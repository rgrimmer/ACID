package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entities.User;
import org.acid.ejb.entitymanager.EntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @EJB(mappedName = "entityManager")
    private EntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(HttpServletRequest request,
                                  @RequestParam(value = "inputEmail", required = true) String inputEmail,
                                  @RequestParam(value = "inputPassword", required = true) String inputPassword) {
        User user = entityManager.getUserByEmailAddress(inputEmail);
        if (user == null) {
            logger.debug("LoginController", "User does not exist");
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("errorMsg", "There isn't an account for this email");
            return mv;
        }
        if (!entityManager.isGoodPassword(inputPassword, user)) {
            logger.debug("LoginController", "Bad password");
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("errorMsg", "Invalid password");
            return mv;
        }
        logger.debug("LoginController", "User '" + user.getName() + "' connected");
        return new ModelAndView("redirect:/");
    }
}
