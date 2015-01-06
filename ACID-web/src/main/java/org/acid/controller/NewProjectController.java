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
    public String register(Model model, HttpSession request) {
        if (((User) request.getAttribute("user")) == null) {
            return "login";
        }
        return "newProject";
    }

    @RequestMapping(value = "/newProject", method = RequestMethod.POST)
    public ModelAndView registerPost(HttpServletRequest request,
            @RequestParam(value = "inputProjectName", required = true) String inputProjectName) {
        ModelAndView mv = new ModelAndView("newProject");
        if (inputProjectName.length() > MAX_PROJECT_NAME_LENGTH) {
            mv.addObject("errorMsg", "Project name  must be less than 256 caracters");
            return mv;
        }
        Project project = null;
        try {
            project = entityManager.createProject(inputProjectName, ((User) request.getSession().getAttribute("user")));
        } catch (PersistenceException ex) {
            logger.error("NewProjectController", "Could not persist project", ex);
        }
        if (project == null) {
            logger.error("NewProjectController", "Could not insert the project in the DB");
            mv.addObject("errorMsg", "An error occurred during creating new project.");
            return mv;
        }

        mv = new ModelAndView("redirect:/home");
        return (mv);

    }
}
