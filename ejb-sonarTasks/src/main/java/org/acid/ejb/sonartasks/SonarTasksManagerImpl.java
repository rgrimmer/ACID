package org.acid.ejb.sonartasks;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;
import org.acid.ejb.logger.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.parser.ParseException;

@Stateless(mappedName = "sonar")
public class SonarTasksManagerImpl implements SonarTasksManager {

    public static final String SONAR_USERNAME = "acid";
    public static final String SONAR_PASSWORD = "acid";

    @EJB(mappedName = "logger")
    private Logger logger;

    public List<Task> getTasks(Project project) {
        String hostName = project.getSonarUrl();
        if (hostName == null) {
            return null;
        }
        String json;
        try {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(hostName + "api/issues/search?sort=UPDATE_DATE&asc=true&pageSize=-1&componentRoots=" + project.getName());
            client.executeMethod(method);
            checkResult(method.getStatusCode());
            json = method.getResponseBodyAsString();
        } catch (IOException ex) {
            logger.error("SonarTasks", "IOException while getting tasks", ex);
            return null;
        } catch (IllegalArgumentException ex) {
            logger.error("SonarTasks", "IllegalArgumentException while getting tasks", ex);
            return null;
        }
        List<Task> tasks = null;
        try {
            tasks = SonarTasksJsonParser.parse(json);
            logger.debug("SonarTasks", tasks.size() + " issues detected");
        } catch (ParseException ex) {
            logger.error("SonarTasks", "Could not parse JSON", ex);
        }
        return tasks;
    }

    private void checkResult(int i) throws IOException {
        logger.debug("SonarTasks", "Status = " + i);
        if (i / 100 != 2) {
            throw new IOException("Error: " + i);
        }
    }
}
