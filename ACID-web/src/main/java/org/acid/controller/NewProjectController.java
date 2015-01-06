package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entitymanager.EntityManager;
import org.acid.ejb.entitymanager.EntityManagerImpl;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewProjectController {

    public static final int MAX_PROJECT_NAME_LENGTH = 256;
    
    @EJB(mappedName = "logger")
    private Logger logger;
    
    @EJB(mappedName = "entityManager")
    private EntityManager entityManager;

    @RequestMapping("/newProject")
    public String register(Model model) {
        return "newProject";
    }

    @RequestMapping(value = "/newProject", method = RequestMethod.POST)
    public ModelAndView registerPost(HttpServletRequest request,
                                     @RequestParam(value = "inputProjectName", required = true) String inputProjectName) {
        System.out.println(inputProjectName);
        ModelAndView mv = new ModelAndView("newProject");
        if(inputProjectName.length() > MAX_PROJECT_NAME_LENGTH){
            mv.addObject("errorMsg", "Project name  must be less than 256 caracters");
            return mv;
        }        
        mv = new ModelAndView("newBoard");
        return (mv);
        
    }
}
