package org.acid.ejb.entitymanager;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;
import org.acid.ejb.entities.Type;
import org.acid.ejb.entities.User;

@Remote
public interface ACIDEntityManager {

    /*
     ***********************************
     * Users methods
     ***********************************
     */
    User createUser(String email, String name, String password);

    User getUserById(int id);

    User getUserByEmailAddress(String emailAddress);
    
    void addUserToProject(User user, Project project);

    boolean isCorrectPassword(String password, User user);

    /*
     ***********************************
     * Board methods
     ***********************************
     */
    Board getBoardById(int id);

    Collection<Board> getBoardsByIdProject(int id);

    Board addBoardToProject(Project project, String boardName, String boardLabel);
    
    Board getBugBoard(Project project);
    
    /*
     ***********************************
     * List methods
     ***********************************
     */
    void addListToBoard(Board board, String label);
    
    org.acid.ejb.entities.List getListByLabel(String label);
    
    /*
     ***********************************
     * Project methods
     ***********************************
     */
    Project createProject(String name, String inputJenkinsUrl, String inputSonarUrl, User owner);

    Project getProjectById(int idProject);
    
    Project getProjectByNameAndOwner(String name, User user);
    
    java.util.List<Project> getProjectsByUser(User user);
    
    void removeProject(Project project);

    /*
     ***********************************
     * Task methods
     ***********************************
     */
    Task createTask(int idTask, String label, String description, int priority);

    void moveTask(int idTask, int idBoardDest);

    Task getTaskById(int id);
    
    int insertTaskToBugBoard(Project project, List<Task> tasks);
    
    java.util.List<Task> getTasks(int boardId);
    
    java.util.List<Task> getSonarTasks(int boardId);
    
    java.util.List<Task> getNewSonarTasks(java.util.List<Task> boardTasks, java.util.List<Task> newTasks);

    /*
     ***********************************
     * Type methods
     ***********************************
     */
    Type getTypeByLabel(String label);
}
