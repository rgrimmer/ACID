/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ejb.jenkinsconnector;

import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import org.acid.ejb.logger.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

@Stateful(mappedName = "jenkinsConnector")
public class JenkinsConnectorImpl implements JenkinsConnector {

    @EJB(mappedName = "logger")
    private Logger logger;

    private HttpClient client;

    /**
     * Make a secured connection to a jenkins webApi.
     *
     * @param hostName uri of jenkins (http://hostname.tld:port/)
     * @param user username
     * @param password password
     *
     */
    @Override
    public void secureConnect(String hostName, String user, String password) {
        client = new HttpClient();
        //hostName = "http://hadrien-belkebir.fr:8080/";
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
    public Object getProjectList(String hostname) throws Exception {

        GetMethod getList = new GetMethod(hostname + "api/xml");
        client.executeMethod(getList);
        logger.debug("xmlDisplay", getList.getResponseBodyAsString());
        return null;
    }
}
