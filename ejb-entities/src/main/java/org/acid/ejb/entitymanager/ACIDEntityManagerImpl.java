package org.acid.ejb.entitymanager;

import java.util.Collection;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.List;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;
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
    public void addProjectToUser(User user, Project project) {
        //Query q = em.createNamedQuery("User.addProject").setParameter("idP", project.getIdProject()).setParameter("idU", user.getIdUser());
        //q.executeUpdate();
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
        return em.find(Board.class, id);
    }

    @Override
    public Collection<Board> getBoardsByIdProject(int id) {
        Project p = em.find(Project.class, id);
        return (p == null) ? null : p.getBoardCollection();
    }

    @Override
    public Board addBoardToProject(Project project, String boardName, String boardLabel) {
        Type type = getTypeByLabel(boardLabel);
        if (type == null) {
            logger.error("EJB-entities", "Type \"" + "\" does not exist. Board \"" + boardName + "\" not created.");
            return null;
        }
        Board board = new Board(boardName, type, project);
        em.persist(board);
        logger.info("EJB-entities", "Board \"" + boardName + "\" added to project \"" + project.getName() + "\"");
        return board;
    }

    @Override
    public Board getBugBoard(Project project) {
        Board bugBoard = null;
        for (Board b : project.getBoardCollection()) {
            if ("BugFix".equals(b.getIdType().getLabel())) {
                bugBoard = b;
            }
        }
        return bugBoard;
    }

    /*
     ***********************************
     * List methods
     ***********************************
     */
    @Override
    public void addListToBoard(Board board, String label) {
        org.acid.ejb.entities.List list = new org.acid.ejb.entities.List(label);
        em.persist(list);
        board.addList(list);
    }

    @Override
    public org.acid.ejb.entities.List getListByLabel(String label) {
        Query query = em.createNamedQuery("List.findByLabel")
                .setParameter("label", label);
        return (org.acid.ejb.entities.List) getSingleResultOrNull(query);
    }

    /*
     ***********************************
     * Project methods
     ***********************************
     */
    @Override
    public Project createProject(String name, String inputJenkinsUrl, String inputSonarUrl, User owner) {
        Project project = new Project(name, inputJenkinsUrl, inputSonarUrl, owner);
        em.persist(project);
        logger.info("EJB-entities", "Project " + project.getIdProject() + " '" + project.getName() + "' (user=" + project.getIdOwner().getIdUser() + ") persisted in DB");
        Board backlog = addBoardToProject(project, "Backlog", "Backlog");
        addListToBoard(backlog, "TODO NEXT");
        Board bugfix = addBoardToProject(project, "Bug report", "BugFix");
        addListToBoard(bugfix, "TODO");
        addListToBoard(bugfix, "DOING");
        addListToBoard(bugfix, "DONE");
        addListToBoard(bugfix, "DONE DONE");
        Board sprint = addBoardToProject(project, "Sprint 1", "Sprint");
        addListToBoard(sprint, "TODO");
        addListToBoard(sprint, "DOING");
        addListToBoard(sprint, "DONE");
        addListToBoard(sprint, "DONE DONE");
        return project;
    }

    @Override
    public Project getProjectById(int idProject) {
        return em.find(Project.class, idProject);
    }

    @Override
    public Project getProjectByNameAndOwner(String name, User user) {
        Query query = em.createNamedQuery("Project.findByNameAndOwner")
                .setParameter("name", name)
                .setParameter("owner", user);
        return (Project) getSingleResultOrNull(query);
    }

    /*
     ***********************************
     * Type methods
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
     * Tasks methods
     ***********************************
     */
    @Override
    public Task createTask(int idTask, String label, String description, int priority) {
        Task task = new Task(idTask, label, description, priority);
        em.persist(task);
        return task;
    }

    @Override
    public void moveTask(int idTask, int idList) {
        Task task = em.find(Task.class, idTask);
        List list = em.find(List.class, idList);
        task.setIdList(list);
    }

    @Override
    public Task getTaskById(int id) {
        return em.find(Task.class, id);
    }

    @Override
    public java.util.List<Task> getTasks(int boardId) {
        java.util.List<Task> tasks = new LinkedList<Task>();
        Query query = em.createQuery("FROM Task WHERE id_board = :boardId");
        query.setParameter("boardId", boardId);
        java.util.List rawList = query.getResultList();
        if (rawList != null) {
            for (Object o : rawList) {
                tasks.add((Task) o);
            }
        }
        return tasks;
    }

    @Override
    public java.util.List<Task> getSonarTasks(int boardId) {
        java.util.List<Task> sonarTasks = new LinkedList<Task>();
        java.util.List<Task> tasks = getTasks(boardId);
        for (Task task : tasks) {
            if (task.getIdSonar() != null && !task.getIdSonar().isEmpty()) {
                sonarTasks.add(task);
            }
        }
        return sonarTasks;
    }

    @Override
    public java.util.List<Task> getNewSonarTasks(java.util.List<Task> boardTasks, java.util.List<Task> newTasks) {
        java.util.List<Task> newSonarTasks = new LinkedList<Task>();
        for (Task newTask : newTasks) {
            boolean add = true;
            for (Task task : boardTasks) {
                if (newTask.getIdSonar().equals(task.getIdSonar())) {
                    add = false;
                    break;
                }
            }
            if (add) {
                newSonarTasks.add(newTask);
            }
        }
        return newSonarTasks;
    }

    @Override
    public int insertTaskToBugBoard(Project project, java.util.List<Task> tasks) {
        Board bugBoard = getBugBoard(project);
        if (bugBoard == null) {
            return -1;
        }
        logger.debug("EJB-entities", "insertTaskToBugBoard: BugFix board found");
        List todoList = bugBoard.getFirstList();
        if (todoList == null) {
            logger.debug("EJB-entities", "insertTaskToBugBoard: No list to add");
            return -1;
        }
        int nbInserted = 0;
        java.util.List<Task> sonarTasksOfBoard = getSonarTasks(bugBoard.getIdBoard());
        java.util.List<Task> newSonarTasks = getNewSonarTasks(sonarTasksOfBoard, tasks);
        for (Task t : newSonarTasks) {
            t.setIdBoard(bugBoard);
            t.setIdList(todoList);
            try {
                em.persist(t);
                nbInserted++;
            } catch (Exception ex) {
                logger.error("EJB-entities", "Could not persist a Sonar task", ex);
            }
        }
        logger.debug("EJB-entities", "insertTaskToBugBoard: " + nbInserted + " tasks inserted on " + tasks.size());
        return nbInserted;
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
