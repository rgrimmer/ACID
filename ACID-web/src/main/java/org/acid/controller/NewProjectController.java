package org.acid.controller;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.User;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewProjectController {

    public static final int MAX_PROJECT_NAME_LENGTH = 256;

    @EJB(mappedName = "logger")
    private Logger logger;

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @RequestMapping("/newProject")
    public String newProject(Model model, HttpSession session) {
        if (((User) session.getAttribute("user")) == null) {
            return "redirect:/login";
        }
        return "newProject";
    }

    @RequestMapping(value = "/newProject", method = RequestMethod.POST)
    public ModelAndView newProjectPost(HttpSession session,
                                     HttpServletRequest request,
                                     @RequestParam(value = "inputProjectName", required = true) String inputProjectName,
                                     @RequestParam(value = "inputJenkinsUrl", required = false) String inputJenkinsUrl,
                                     @RequestParam(value = "inputSonarUrl", required = false) String inputSonarUrl) {
        if (inputProjectName.length() > MAX_PROJECT_NAME_LENGTH) {
            ModelAndView mv = new ModelAndView("newProject");
            mv.addObject("errorMsg", "Project name's length must be less than " + MAX_PROJECT_NAME_LENGTH + " caracters.");
            return mv;
        }
        User user = (User) session.getAttribute("user");
        if (entityManager.getProjectByNameAndOwner(inputProjectName, user) != null) {
            ModelAndView mv = new ModelAndView("newProject");
            mv.addObject("errorMsg", "You already have a project named \"" + inputProjectName + "\".");
            return mv;
        }

        Project project = null;
        try {
            project = entityManager.createProject(inputProjectName, inputJenkinsUrl, inputSonarUrl, ((User) request.getSession().getAttribute("user")));
        } catch (PersistenceException ex) {
            logger.error("NewProjectController", "Could not persist project", ex);
        }
        if (project == null) {
            logger.error("NewProjectController", "Could not insert the project in the DB.");
            ModelAndView mv = new ModelAndView("newProject");
            mv.addObject("errorMsg", "An error occurred during creating new project.");
            return mv;
        }
        return new ModelAndView("redirect:/home");
    }
}
