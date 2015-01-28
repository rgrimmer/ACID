package org.acid.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.User;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.jenkinsconnector.JenkinsConnector;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    public static final String JENKINS_USERNAME = "acid";
    public static final String JENKINS_PASSWORD = "acid";

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @EJB(mappedName = "jenkinsConnector")
    private JenkinsConnector jenkins;

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView addUserToProjectPost(HttpServletRequest request,
                                             @RequestParam(value = "inputEmail", required = true) String inputEmail,
                                             @RequestParam(value = "inputIdProject", required = true) String intputIdProject) {
        User user = entityManager.getUserByEmailAddress(inputEmail);
        Project project = entityManager.getProjectById(Integer.parseInt(intputIdProject));
        if (project == null) {
            logger.debug("HomeController", "Add user to project: project " + intputIdProject + " not found");
            return new ModelAndView("redirect:/home");
        }
        logger.debug("HomeController", "trying to add User " + user.getIdUser() + " to project " + project.getIdProject());
        // TODO:
        //entityManager.addUserToProject(user, project);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/home")
    public ModelAndView home(Model model, HttpSession request) {
        User user = (User) request.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mv = new ModelAndView("home");

        String listProject = "";
        List<Project> projects = entityManager.getProjectsByUser(user);
        logger.debug("HomeController", "Sizeof project collection: " + projects.size());
        for (Project project : projects) {
            boolean onJenkins = (project.getJenkinsUrl() != null && !project.getJenkinsUrl().isEmpty());
            String jenkinsStatus = null;

            if (onJenkins) {
                jenkins.secureConnect(project.getJenkinsUrl(), JENKINS_USERNAME, JENKINS_PASSWORD);
                org.acid.ejb.jenkinsconnector.data.Project jenkinsProject = jenkins.getProjectByName(project.getName());
                if (jenkinsProject == null) {
                    logger.warn("HomeController", "Project \"" + project.getName() + "\" not found on Jenkins");
                    mv.addObject("errorMsg", "Project \"" + project.getName() + "\" not found on Jenkins");
                    return mv;
                }
                jenkinsStatus = jenkins.getStatus(jenkinsProject);
            }

            listProject += "<div class=\"board-title\" >" + project.getName() + ""
                    + "<span class=\"glyphicon glyphicon-folder-open\"><span>"
                    + ((project.getSonarUrl() == null || project.getSonarUrl().isEmpty()) ? "" : " | <a href=\"sonar?idProject=" + project.getIdProject() + "\">Refresh Sonar<span class=\"glyphicon glyphicon-refresh\"><span></a>")
                    + ((onJenkins) ? " | Jenkins status: " + jenkinsStatus : "")
                    + " | <a href=\"removeProject?idProject=" + project.getIdProject() + "\">Remove<span class=\"glyphicon glyphicon-remove-sign\"><span></a>"
                    + "</div>";
//            listProject += "<div class=\"container\"><div class=\"row\" ><div class=\"col-sm-3 col-sm-offset-1 blog-sidebar\" ><div class=\"sidebar-module\">"
//                    + "         <form class=\"form-addUser\" role=\"form\" method=\"POST\" action=\"" + request.getServletContext().getContextPath() + "/home\">"
//                    + "             <h2 class=\"form-addUser-heading\">Add user to project</h2>"
//                    + "             <label for=\"inputEmail\" class=\"sr-only\">User email address</label>"
//                    + "             <input type=\"email\" id=\"inputEmail\" name=\"inputEmail\" class=\"form-control\" placeholder=\"Email address\" required autofocus>"
//                    + "             <input type=\"hidden\" id=\"inputIdProject\" name=\"inputIdProject\" value=\"" + project.getIdProject() + "\" required>"
//                    + "             <button id=\"submitBtn\" class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Add</button>"
//                    + "         </form>"
//                    + "     </div></div></div></div>";
            for (Board board : project.getBoardCollection()) {
                listProject += "<div class=\"col-sm-3\"><a href=\"board?idBoard=" + board.getIdBoard() + "\"><div class=\"panel board\"><div class=\"panel-heading\"><h3 class=\"panel-title\">"
                        + board.getName()
                        + "</h3></div></div></a></div>";
            }
            listProject += "<hr>";
        }

        mv.addObject("projets", listProject);
        return mv;
    }
}
