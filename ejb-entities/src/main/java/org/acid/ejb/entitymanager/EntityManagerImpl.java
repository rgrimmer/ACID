package org.acid.ejb.entitymanager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acid.ejb.entities.User;

@Stateless(mappedName = "entityManager")
public class EntityManagerImpl implements org.acid.ejb.entitymanager.EntityManager {

    @PersistenceContext
    public EntityManager em;

    public EntityManagerImpl() {
    }

    @Override
    public User getUserById(int id) {
        System.out.println("getUserById");
        return em.find(User.class, id);
    }

    public User getUserByEmailAddress(String emailAddress) {
        System.out.println("getUserByEmailAddress");
        return (User) getSingleResultOrNull("SELECT u FROM User u WHERE u.email = '" + emailAddress + "'");
    }
    
    private Object getSingleResultOrNull(String queryString) {
        Query query = em.createQuery(queryString);
        Object result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return result;
    }
}
