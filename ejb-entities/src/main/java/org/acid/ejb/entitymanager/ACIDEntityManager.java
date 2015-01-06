package org.acid.ejb.entitymanager;

import java.util.Collection;
import javax.ejb.Remote;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.Project;
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

    boolean isCorrectPassword(String password, User user);
    
    
    /*
     ***********************************
     * Board methods
     ***********************************
     */
    
    Board createBoard(String name, Type type, int idProject);
    
    Board getBoardById(int id);
    
    Collection<Board> getBoardsByIdProject(int id);
    
    /*
     ***********************************
     * Project methods
     ***********************************
     */
    Project createProject(String name, User owner);
    Project getProjectById(int idProject);
    
    /*
     ***********************************
     * Type methods
     ***********************************
     */
    
    Type getTypeByLabel(String label);
}
