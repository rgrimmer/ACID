package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class LogoutController {

    @EJB(mappedName = "entityManager")
    private EntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/logout")
    public String login(Model model, HttpSession session) {
        logger.debug("LoginController", "Destroy user-session for user : '" + ((User)session.getAttribute("user")).getName() + "'");
        session.setAttribute("user", null);
        return ("redirect:/login");
    }

}
