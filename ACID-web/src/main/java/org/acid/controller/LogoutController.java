package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.User;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        logger.debug("LoginController", "Destroy user-session for user : '" + ((User) session.getAttribute("user")).getName() + "'");
        session.setAttribute("user", null);
        return ("redirect:/login");
    }
}
