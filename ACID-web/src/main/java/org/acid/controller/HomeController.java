package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.User;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView addUserToProjectPost(HttpServletRequest request,
            @RequestParam(value = "inputEmail", required = true) String inputEmail,
            @RequestParam(value = "inputIdProject", required = true) String intputIdProject) {
        User user = entityManager.getUserByEmailAddress(inputEmail);
        Project project = entityManager.getProjectById(Integer.parseInt(intputIdProject));
        logger.debug("[Add project to user]", "work");
        entityManager.addProjectToUser(user, project);
        logger.debug("[Add project to user]", "project"+project.getName() + "user "+user.getName());
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/home")
    public ModelAndView home(Model model, HttpSession request) {
        if (((User) request.getAttribute("user")) == null) {
            return new ModelAndView("redirect:/login");
        }

        int id = 1;

        User user = entityManager.getUserById(id);
        ModelAndView mv = new ModelAndView("home");
        String listProject = "";

        for (Project project : user.getProjectCollection()) {
            listProject += "<div class=\"board-title\" >" + project.getName() + " <span class=\"glyphicon glyphicon-folder-open\"></div>";
            listProject += "<div class=\"container\"><div class=\"row\" ><div class=\"col-sm-3 col-sm-offset-1 blog-sidebar\" ><div class=\"sidebar-module\">"
                    + "         <form class=\"form-addUser\" role=\"form\" method=\"POST\" action=\"" + request.getServletContext().getContextPath() + "/home\">"
                    + "             <h2 class=\"form-addUser-heading\">Add user to project</h2>"
                    + "             <label for=\"inputEmail\" class=\"sr-only\">User email address</label>"
                    + "             <input type=\"email\" id=\"inputEmail\" name=\"inputEmail\" class=\"form-control\" placeholder=\"Email address\" required autofocus>"
                    + "             <input type=\"hidden\" id=\"inputIdProject\" name=\"inputIdProject\" value=\""+ project.getIdProject() +"\" class=\"form-control\" required>"
                    + "             <button id=\"submitBtn\" class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Add</button>"
                    + "         </form>"
                    + "     </div></div></div></div>";
            for (Board board : project.getBoardCollection()) {
                listProject += "<div class=\"col-sm-3\"><a href=\"board?idBoard="+board.getIdBoard()+"\"><div class=\"panel board\"><div class=\"panel-heading\"><h3 class=\"panel-title\">"
                        + board.getName()
                        + "</h3></div></div></a></div>";
            }

            listProject += "<hr>";
        }
        mv.addObject("projets", listProject);
        return mv;
    }

}
