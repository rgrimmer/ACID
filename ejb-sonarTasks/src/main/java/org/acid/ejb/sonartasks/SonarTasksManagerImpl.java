/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acid.ejb.sonartasks;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import org.acid.ejb.entities.Task;
import org.acid.ejb.logger.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author maxence
 */
public class SonarTasksManagerImpl implements SonarTasks {

    @EJB(mappedName = "logger")
    Logger logger;

    public List<Task> getTasks() {
        String json = "";
        try {
            HttpClient client = new HttpClient();

            String hostName = "http://http://hadrien-belkebir.fr:8888/";
            GetMethod loginLink = new GetMethod(hostName + "loginEntry");
            client.executeMethod(loginLink);
            checkResult(loginLink.getStatusCode());

            String location = hostName + "sessions/login";
            while (true) {
                PostMethod loginMethod = new PostMethod(location);
                loginMethod.addParameter("login", "maxence");
                loginMethod.addParameter("password", "maxence");
                loginMethod.addParameter("commit", "submit");
                try {
                    client.executeMethod(loginMethod);
                } catch (IOException ex) {
                    logger.info("Exception", "IOException");
                }
                if (loginMethod.getStatusCode() / 100 == 3) {
                    // Commons HTTP client refuses to handle redirects for POST
                    // so we have to do it manually.
                    location = loginMethod.getResponseHeader("Location").getValue();
                    continue;
                }
                checkResult(loginMethod.getStatusCode());
                break;
            }

            HttpMethod method = new GetMethod(hostName + "api/issues/search?sort=UPDATE_DATE&asc=true&pageSize=-1&componentRoots=MJJ.M1Groupe2:InterpreteurJJC");
            client.executeMethod(method);
            checkResult(method.getStatusCode());
            json = method.getResponseBodyAsString();

            System.out.println(method.getResponseBodyAsString());
        } catch (IOException ex) {
            logger.info("Exception", "IOException");
        }
        
        logger.info("JSON", json);
        return null;
    }

    private static void checkResult(int i) throws IOException {
        if (i / 100 != 2) {
            throw new IOException();
        }
    }
}

