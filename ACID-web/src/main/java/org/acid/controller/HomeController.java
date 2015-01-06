package org.acid.controller;

import javax.ejb.EJB;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.User;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entitymanager.EntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @EJB(mappedName = "entityManager")
    private EntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/home")
    public ModelAndView home(Model model) {
        int id = 1;
        
        User user = entityManager.getUserById(id);
        ModelAndView mv = new ModelAndView("home");
        String listProject = "";

        for (Project project : user.getProjectCollection()) {
            listProject += "<div class=\"board-title\">" + project.getName() + "</div>";

//            for(Board board : project.getBoardCollection()) {
//            listProject += "<div class=\"col-sm-3\"><div class=\"panel board\"><div class=\"panel-heading\"><h3 class=\"panel-title\">"
//                    + board.getName()
//                    + "</h3></div></div></div>";
//            }
        }
        mv.addObject("projets", listProject);
        return mv;
    }

}
