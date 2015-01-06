package org.acid.ejb.entitymanager;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import org.acid.ejb.entities.Board;
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

    boolean isGoodPassword(String password, User user);
    
    
    /*
     ***********************************
     * Board methods
     ***********************************
     */
    
    Board getBoardById(int id);
    
    Collection<Board> getBoardsByIdProject(int id);
    
}
