package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.User;
import org.acid.ejb.entitymanager.EntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @EJB(mappedName = "entityManager")
    private EntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/board")
    public String board(Model model, HttpSession request) {
        if (((User) request.getAttribute("user")) == null) {
            return "redirect:/login";
        }
        return "board";
    }

}
