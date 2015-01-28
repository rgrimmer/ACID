package org.acid.ejb.sonartasks;

import java.util.List;
import javax.ejb.Remote;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;

@Remote
public interface SonarTasksManager {
    
    List<Task> getTasks(Project project);
}
