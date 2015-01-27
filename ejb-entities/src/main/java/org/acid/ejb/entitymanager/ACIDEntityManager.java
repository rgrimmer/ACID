package org.acid.ejb.entitymanager;

import java.util.Collection;
import javax.ejb.Remote;
import javax.smartcardio.Card;
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
    
    void addProjectToUser(User user, Project project);

    boolean isCorrectPassword(String password, User user);

    /*
    ***********************************
    * Card methods
    ***********************************
    */
    
    Card getCardById(int id);
    
    /*
     ***********************************
     * Board methods
     ***********************************
     */
    Board getBoardById(int id);

    Collection<Board> getBoardsByIdProject(int id);

    Board addBoardToProject(Project project, String boardName, String boardLabel);

    /*
     ***********************************
     * Project methods
     ***********************************
     */
    Project createProject(String name, String inputJenkinsUrl, String inputSonarUrl, User owner);

    Project getProjectById(int idProject);
    
    /*
     ***********************************
     * Task methods
     ***********************************
     */
    
    Task createTask(int idTask, String label, String description, int priority);
    void moveTask(int idTask, int idBoardDest);

    Project getProjectByNameAndOwner(String name, User user);

    /*
     ***********************************
     * Type methods
     ***********************************
     */
    Type getTypeByLabel(String label);

}
