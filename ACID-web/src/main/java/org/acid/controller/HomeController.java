package org.acid.controller;

import java.util.Collection;
import javax.ejb.EJB;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.User;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entitymanager.EntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @EJB(mappedName = "entityManager")
    private EntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/home")
    public String home(Model model, HttpSession request) {
        if (((User) request.getAttribute("user")) == null) {
            return "redirect:/login";
        }
        return "home";
    }

    public ModelAndView home(Model model) {
        int id = 1;

        User user = entityManager.getUserById(id);
        ModelAndView mv = new ModelAndView("home");
        String listProject = "";
        
        for (Project project : user.getProjectCollection()) {
            listProject += "<div class=\"board-title\" >" + project.getName() + " <span class=\"glyphicon glyphicon-folder-open\"></div>";

            for (Board board : project.getBoardCollection()) {
                listProject += "<div class=\"col-sm-3\"><div class=\"panel board\"><div class=\"panel-heading\"><h3 class=\"panel-title\">"
                        + board.getName()
                        + "</h3></div></div></div>";
            }
            
            listProject += "<hr>";
        }
        mv.addObject("projets", listProject);
        return mv;
    }

}
