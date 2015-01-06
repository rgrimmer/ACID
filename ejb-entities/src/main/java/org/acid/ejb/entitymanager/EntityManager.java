package org.acid.ejb.entitymanager;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.User;

@Remote
public interface EntityManager {

    /*
     ***********************************
     * Users methods
     ***********************************
     */
    int createUser(User user);

    User getUserById(int id);

    User getUserByEmailAddress(String emailAddress);
    
    boolean isGoodPassword(String password, User user);
    
    
    /*
     ***********************************
     * Board methods
     ***********************************
     */
    
    Collection<Board> getBoardsByIdProject(int id);
    
}
