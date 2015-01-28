package org.acid.ejb.jenkinsconnector;

import java.util.List;
import javax.ejb.Remote;
import org.acid.ejb.jenkinsconnector.data.Project;

@Remote
public interface JenkinsConnector {

    void secureConnect(String hostname, String user, String password);

    List<Project> getProjectList();
    
    Project getProjectByName(String projectName);
    
    String getStatus(Project project);
}
