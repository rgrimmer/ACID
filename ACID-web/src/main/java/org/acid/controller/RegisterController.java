package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entitymanager.EntityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(HttpServletRequest request,
            @RequestParam(value = "inputUsername", required = true) String inputUsername,
            @RequestParam(value = "inputEmail", required = true) String inputEmail,
            @RequestParam(value = "inputPassword", required = true) String inputPassword,
            @RequestParam(value = "inputPasswordConfirmation", required = true) String inputPasswordConfirmation) {
        System.out.println(inputUsername);
        System.out.println(inputEmail);
        System.out.println(inputPassword);
        System.out.println(inputPasswordConfirmation);
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("errorMsg", "ERREUR !!!!!!");
        return mv;
    }
}
