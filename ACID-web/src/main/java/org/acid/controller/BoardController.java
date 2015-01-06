package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.User;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping("/board")
    public String board(Model model, HttpSession request) {
        if (((User) request.getAttribute("user")) == null) {
            return "redirect:/login";
        }
        return "board";
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ModelAndView boardPost(HttpServletRequest request,
                                  @RequestParam(value = "id", required = true) String id,
                                  @RequestParam(value = "data", required = true) String data) {
        // TODO: add to db

        ModelAndView mv = new ModelAndView("board");
        mv.addObject("infoMsg", "id=" + id + " data=" + data);
        return mv;
    }

}
