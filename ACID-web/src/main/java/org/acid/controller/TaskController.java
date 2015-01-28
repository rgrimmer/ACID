package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entities.Task;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public ModelAndView card(Model model, @RequestParam(required = true) Integer idTask) {
        logger.debug("TaskController", "request get");
        ModelAndView mv = new ModelAndView("task");
        mv.addObject("idTask", idTask);
        String taskStr = "";

        Task task = entityManager.getTaskById(idTask);

        taskStr += "<div id=\"task\" class=\"panel panel-primary center\">"
                + "<div class=\"panel-heading\">"
                + "<h3 class=\"panel-title\"><span class=\"glyphicon glyphicon-file\"></span>" + task.getLabel() + "</h3>"
                + "</div>"
                + "<div class=\"panel-body\"><span class=\"glyphicon glyphicon-list-alt\"></span>" + " Description : " + task.getDescription() + "</div>"
                //+ "<button onClick=\"showConfirmationBox()\" type=\"button\" class=\"btn btn-danger\">"
                //        + "<span class=\"glyphicon glyphicon-remove-sign\"></span> Delete"
                //+ "</button>"
                + "</div>";

        mv.addObject("task", taskStr);

        return mv;
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public ModelAndView taskPost(Model model,
                                 HttpServletRequest request,
                                 @RequestParam(value = "idTask", required = true) Integer idTask) {

        return card(model, idTask);
    }

    @RequestMapping(value = "/task")
    public String task(Model model) {
        logger.debug("TaskController", "redirection");
        return "redirect:/home";
    }
}
