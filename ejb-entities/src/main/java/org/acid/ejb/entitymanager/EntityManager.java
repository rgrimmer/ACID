package org.acid.ejb.entitymanager;

import javax.ejb.Remote;
import org.acid.ejb.entities.User;

@Remote
public interface EntityManager {

    User getUserById(int id);
    
    User getUserByEmailAddress(String emailAddress);
}
