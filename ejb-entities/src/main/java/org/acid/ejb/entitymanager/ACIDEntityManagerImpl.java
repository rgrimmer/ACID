package org.acid.ejb.entitymanager;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.User;
import org.acid.ejb.logger.Logger;
import org.acid.ejb.pwhash.PasswordHash;

@Stateless(mappedName = "entityManager")
public class ACIDEntityManagerImpl implements ACIDEntityManager {

    @PersistenceContext(unitName = "ACID_entities")
    private EntityManager em;

    @EJB(mappedName = "pwhash")
    private PasswordHash pwHash;

    @EJB(mappedName = "logger")
    private Logger logger;

    /*
     ***********************************
     * Users methods
     ***********************************
     */
    @Override
    public User createUser(String email, String name, String password) {
        String hashPassword = pwHash.hash(password);
        User user = new User(email, name, hashPassword);
        em.persist(user);
        logger.info("EJB-entities", "User " + user.getIdUser() + " '" + user.getName() + "' (" + user.getEmail() + ") persisted in DB");
        return user;
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {
        Query query = em.createNamedQuery("User.findByEmail")
                .setParameter("email", emailAddress);
        return (User) getSingleResultOrNull(query);
    }

    @Override
    public boolean isCorrectPassword(String password, User user) {
        return pwHash.equals(password, user.getPassword());
    }

    /*
     ***********************************
     * Boards methods
     ***********************************
     */
    @Override
    public Board getBoardById(int id) {
        Board b = em.find(Board.class, id);
        return b;
    }

    @Override
    public Collection<Board> getBoardsByIdProject(int id) {
        Project p = em.find(Project.class, id);
        return p.getBoardCollection();
    }

    /*
     ***********************************
     * Private methods
     ***********************************
     */
    private Object getSingleResultOrNull(Query query) {
        Object result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return result;
    }
}
