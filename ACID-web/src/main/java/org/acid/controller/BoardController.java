package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.List;
import org.acid.ejb.entities.Project;
import org.acid.ejb.entities.Task;
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
public class BoardController {

    @EJB(mappedName = "entityManager")
    private ACIDEntityManager entityManager;

    @EJB(mappedName = "logger")
    private Logger logger;

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView board(Model model, HttpSession session, @RequestParam(required = true) Integer idBoard) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        Board board = entityManager.getBoardById(idBoard);
        if (board == null) {
            return new ModelAndView("redirect:/home");
        }

        if (!userIsSubscribToBoard(user, board)) {
            return new ModelAndView("redirect:/home");
        }
        
        logger.debug("BoardController", "request get");

        ModelAndView mv = new ModelAndView("board");
        mv.addObject("idBoard", idBoard);

        String lists = "";
        for (List list : board.getListCollection()) {
            lists += "<div class=\"col-sm-3\">"
                    + "<div class=\"panel panel-primary\">"
                    + "<div class=\"panel-heading\">"
                    + "<h3 class=\"panel-title\">" + list.getLabel() + "</h3>"
                    + "</div>"
                    + "<div id=\"draggableContainer" + list.getIdList() + "\" class=\"panel-body\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\">";

            for (Task task : board.getTaskCollection()) {
                if (task.getIdList().getIdList().intValue() != list.getIdList().intValue()) {
                    continue;
                }

                lists += "<div id=\"draggableItem" + task.getIdTask() + "\" class=\"panel-body draggable\" draggable=\"true\" ondragstart=\"drag(event)\">"
                        + task.getDescription() + "<span class=\"glyphicon glyphicon-pencil\"></span>"
                        + "</div>";
            }

            lists += "</div></div></div>";
        }

        mv.addObject("lists", lists);

        return mv;
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ModelAndView boardPost(Model model,
            HttpServletRequest request,
            @RequestParam(value = "idBoard", required = true) Integer idBoard,
            @RequestParam(value = "idT", required = true) Integer idTask,
            @RequestParam(value = "idL", required = true) Integer idList) {

        logger.debug("BoardController", "id tash " + idTask.toString());
        logger.debug("BoardController", "id list " + idList.toString());
        entityManager.moveTask(idTask, idList);

        logger.debug("BoardController", "id board " + idBoard.toString());

        return board(model, request.getSession(), idBoard);
    }

    @RequestMapping(value = "/board")
    public String board(Model model) {
        logger.debug("BoardController", "redirection");
        return "redirect:/home";
    }
    
    
    private boolean userIsSubscribToBoard(User user, Board board) {
        int idProject = board.getIdProject().getIdProject();
        for (Project project : user.getProjectCollection()) {
            if (project.getIdProject() == idProject)
                return true;
        }
        return false;
    }
}
