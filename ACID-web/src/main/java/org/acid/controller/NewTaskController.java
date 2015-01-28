package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.List;
import org.acid.ejb.entities.Task;
import org.acid.ejb.entities.User;
import org.acid.ejb.entitymanager.ACIDEntityManager;
import org.acid.ejb.logger.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewTaskController {

    public static final int MAX_TASK_LABEL_LENGTH = 64;
    public static final int MAX_TASK_DESCRIPTION_LENGTH = 512;

    @EJB(mappedName = "logger")
    private Logger logger;

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @RequestMapping("/newTask")
    public ModelAndView newTask(HttpSession request,
                                @RequestParam(value = "idBoard", required = true) String idBoard) {
        if (((User) request.getAttribute("user")) == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mv = new ModelAndView("newTask");
        mv.addObject("idBoard", idBoard);
        return mv;
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public ModelAndView newTaskPost(HttpServletRequest request,
                                    @RequestParam(value = "inputTaskLabel", required = true) String inputTaskLabel,
                                    @RequestParam(value = "inputTaskDescription", required = true) String inputTaskDescription,
                                    @RequestParam(value = "idBoard", required = true) String idBoard) {
        ModelAndView mv = new ModelAndView("newTask");
        mv.addObject("idBoard", idBoard);
        if (inputTaskLabel.length() > MAX_TASK_LABEL_LENGTH) {
            mv.addObject("errorMsg", "The label's length must be shorter than " + MAX_TASK_LABEL_LENGTH + " characters");
            return mv;
        }
        if (inputTaskDescription.length() > MAX_TASK_DESCRIPTION_LENGTH) {
            mv.addObject("errorMsg", "The description's length must be shorter than " + MAX_TASK_DESCRIPTION_LENGTH + " characters");
            return mv;
        }
        Board board = entityManager.getBoardById(Integer.parseInt(idBoard));
        if (board == null) {
            mv.addObject("errorMsg", "Board " + idBoard + " not found");
            return mv;
        }
        List list = board.getFirstList();
        if (list == null) {
            mv.addObject("errorMsg", "No list found to add the task");
            return mv;
        }
        Task task = entityManager.createTask(inputTaskLabel, inputTaskDescription, board, list);
        if (task == null) {
            mv.addObject("errorMsg", "Could not create task");
            return mv;
        }
        logger.info("NewTaskController", "Task " + task.getIdTask() + " added in list " + list.getLabel() + " of board " + board.getName());
        return new ModelAndView("redirect:/board?idBoard=" + idBoard);
    }
}
