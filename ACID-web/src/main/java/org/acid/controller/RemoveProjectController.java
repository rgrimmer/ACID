package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RemoveProjectController {

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping(value = "/removeProject", method = RequestMethod.GET)
    public ModelAndView sonar(HttpSession session, @RequestParam(required = true) Integer idProject) {
        Project project = entityManager.getProjectById(idProject);
        if (project == null) {
            logger.warn("RemoveProjectController", "Project ID " + idProject + " not found");
            ModelAndView mv = new ModelAndView("home");
            mv.addObject("errorMsg", "Project ID " + idProject + " not found");
            return mv;
        }
        //entityManager.removeProject(project);
        return new ModelAndView("redirect:/home");
    }
}
