package org.acid.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.acid.ejb.sonartasks.SonarTasksManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SonarController {

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;
    
    @EJB(mappedName = "sonar")
    private SonarTasksManager sonar;

    @RequestMapping(value = "/sonar", method = RequestMethod.GET)
    public ModelAndView sonar(HttpSession session, @RequestParam(required = true) Integer idProject) {
        Project project = entityManager.getProjectById(idProject);
        if (project == null) {
            logger.warn("SonarController", "Project ID " + idProject + " not found");
            ModelAndView mv = new ModelAndView("home");
            mv.addObject("errorMsg", "Project ID " + idProject + " not found");
            return mv;
        }
        List<Task> tasks = sonar.getTasks(project);
        if (tasks != null) {
            entityManager.insertTaskToBugBoard(project, tasks);
        }
        return new ModelAndView("redirect:/home");
    }
}
