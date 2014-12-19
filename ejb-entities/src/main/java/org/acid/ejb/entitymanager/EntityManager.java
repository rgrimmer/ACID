package org.acid.ejb.entitymanager;

import javax.ejb.Remote;
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
}
