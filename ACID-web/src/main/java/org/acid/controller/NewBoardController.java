package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class NewBoardController {

    public static final int MAX_PROJECT_NAME_LENGTH = 256;

    @EJB(mappedName = "logger")
    private Logger logger;

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @RequestMapping("/newBoard")
    public String newBoard(Model model, HttpSession request) {
        if (((User) request.getAttribute("user")) == null) {
            return "redirect:/login";
        }
        return "newBoard";
    }

    @RequestMapping(value = "/newBoard", method = RequestMethod.POST)
    public ModelAndView newBoardPost(HttpServletRequest request,
                                     @RequestParam(value = "inputProjectName", required = true) String inputProjectName) {
        ModelAndView mv = new ModelAndView("newBoard");
        if (inputProjectName.length() > MAX_PROJECT_NAME_LENGTH) {
            mv.addObject("errorMsg", "Project name  must be less than " + MAX_PROJECT_NAME_LENGTH + " characters");
            return mv;
        }
        return mv;
    }
}
