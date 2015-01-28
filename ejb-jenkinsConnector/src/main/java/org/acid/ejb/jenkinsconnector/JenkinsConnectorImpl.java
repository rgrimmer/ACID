package org.acid.ejb.jenkinsconnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import org.acid.ejb.logger.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.acid.ejb.jenkinsconnector.data.Project;

@Stateful(mappedName = "jenkinsConnector")
public class JenkinsConnectorImpl implements JenkinsConnector {

    @EJB(mappedName = "logger")
    private Logger logger;

    private HttpClient client;

    private String hostName;

    /**
     * Make a secured connection to a jenkins webApi.
     *
     * @param hostName uri of jenkins (http://hostname.tld:port/)
     * @param user     username
     * @param password password
     *
     */
    @Override
    public void secureConnect(String hostName, String user, String password) {
        client = new HttpClient();
        this.hostName = hostName;
        GetMethod loginLink = null;
        try {
            loginLink = new GetMethod(hostName + "loginEntry");
        } catch (Exception ex) {
            logger.error("Connection Error", "Invalid jenkins configuration : " + ex.getMessage(), ex);
            return;
        }
        try {
            client.executeMethod(loginLink);
        } catch (IOException ex) {
            logger.error("Connection Error", "Connection to Jenkins failed, Aborting...", ex);
            return;
        }
        try {
            checkResult(loginLink.getStatusCode());
        } catch (IOException ex) {
            logger.error("Connection Error", ex.getMessage() + "\nAborting", ex);
            return;
        }
        String location = hostName + "j_acegi_security_check";
        while (true) {
            PostMethod loginMethod = new PostMethod(location);
            loginMethod.addParameter("j_username", user);
            loginMethod.addParameter("j_password", password);
            loginMethod.addParameter("action", "login");
            try {
                client.executeMethod(loginMethod);
            } catch (IOException ex) {
                logger.error("Connection Error", "Fail to identifying to Jenkins", ex);
                return;
            }
            if (loginMethod.getStatusCode() / 100 == 3) {
// Commons HTTP client refuses to handle redirects for POST
// so we have to do it manually.
                location = loginMethod.getResponseHeader("Location").getValue();
                continue;
            }

            try {
                checkResult(loginMethod.getStatusCode());
            } catch (IOException ex) {
                logger.error("Connection Error", ex.getMessage(), ex);
                return;
            }
            break;
        }
        logger.debug("Connection Jenkins", "Secure connection success to " + hostName);

    }

    private static void checkResult(int i) throws IOException {
        if (i / 100 != 2) {
            throw new IOException("Error " + i);
        }
    }

    @Override
    public List<Project> getProjectList() {
        List<Project> ret = new ArrayList<>();
        GetMethod getList = new GetMethod(this.hostName + "api/xml");
        try {
            client.executeMethod(getList);
            XMLParser parser = new XMLParser(getList.getResponseBodyAsStream(), logger);
            ret = parser.fetchProjectList();
        } catch (IOException ex) {
            logger.error("Jenkins connection", "Failed to get project list", ex);
        }
        return ret;
    }

    @Override
    public Project getProjectByName(String projectName) {
        logger.debug("Connection Jenkins", "Project name = " + projectName);
        String[] tokens = projectName.split(":");
        String name = tokens[tokens.length - 1];
        logger.debug("Connection Jenkins", "name = " + name);
        List<Project> projects = getProjectList();
        if (projects != null) {
            for (Project p : projects) {
                logger.debug("Connection Jenkins", "name loop = " + p.getProjectName());
                if (p.getProjectName().equals(name)) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public String getStatus(Project project) {
        switch (project.getColor()) {
            case BLUE:
                return "SUCCESS";
            case RED:
                return "FAILED";
            case YELLOW:
                return "UNSTABLE";
            case BLUE_ANIME:
                return "RUNNING... (last: SUCCESS)";
            case RED_ANIME:
                return "RUNNING... (last: FAILED)";
            case YELLOW_ANIME:
                return "RUNNING... (last: UNSTABLE)";
        }
        return "Unknown";
    }

}
