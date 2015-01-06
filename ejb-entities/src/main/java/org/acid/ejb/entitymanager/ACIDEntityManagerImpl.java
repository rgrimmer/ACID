package org.acid.ejb.entitymanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Type;
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
    public boolean isGoodPassword(String password, User user) {
        return pwHash.equals(password, user.getPassword());
    }

    /*
     ***********************************
     * Boards methods
     ***********************************
     */
    @Override
    public Board createBoard(String name, Type type, int idProject) {
        Project project = getProjectById(idProject);
        if (project == null) {
            return null;
        }
        Board board = new Board(name, type, project);
        em.persist(board);
        return board;
    }

    @Override
    public Board getBoardById(int id) {
        return em.find(Board.class, id);
    }

    @Override
    public Collection<Board> getBoardsByIdProject(int id) {
        Project p = em.find(Project.class, id);
        return (p == null) ? null : p.getBoardCollection();
    }

    /*
     ***********************************
     * Project methods
     ***********************************
     */
    @Override
    public Project createProject(String name, User owner) {
        Project project = new Project(name, owner);
        Board backlog = new Board("Backlog", getTypeByLabel("Backlog"), project);
        logger.info("EJB-entities", "BOARD  " + backlog.getName());
        project.addBoard(backlog);
        project.addBoard(new Board("BugFix", getTypeByLabel("BugFix"), project));
        project.addBoard(new Board("Sprint 1", getTypeByLabel("Sprint"), project));
        em.persist(project);
        logger.info("EJB-entities", "User " + project.getIdProject() + " '" + project.getName() + "' (" + project.getIdOwner() + ") persisted in DB");
        return project;
    }

    public Project getProjectById(int idProject) {
        return em.find(Project.class, idProject);
    }
    
    /*
     ***********************************
     * Project methods
     ***********************************
     */
    
    @Override
    public Type getTypeByLabel(String label) {
        Query query = em.createNamedQuery("Type.findByLabel")
                .setParameter("label", label);
        return (Type) getSingleResultOrNull(query);
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
