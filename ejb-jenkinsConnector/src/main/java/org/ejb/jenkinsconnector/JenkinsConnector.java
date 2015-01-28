/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ejb.jenkinsconnector;

import java.util.List;
import javax.ejb.Remote;
import org.ejb.jenkinsconnector.data.Project;

/**
 *
 * @author hadryx
 */
@Remote
public interface JenkinsConnector {

    void secureConnect(String hostname, String user, String password);

    List<Project> getProjectList();

}
