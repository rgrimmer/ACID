package org.acid.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.acid.ejb.entities.User;
import javax.servlet.http.HttpServletRequest;
import org.acid.ejb.entities.Board;
import org.acid.ejb.entities.List;
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

    @RequestMapping("/board")
    public ModelAndView board(Model model) {
        ModelAndView mv = new ModelAndView("board");
        String lists = "";
        Board board = entityManager.getBoardById(2);

        for (List list : board.getListCollection()) {
            lists += "<div class=\"col-sm-3\">"
                    + "<div class=\"panel panel-primary\">"
                    + "<div class=\"panel-heading\">"
                    + "<h3 class=\"panel-title\">" + list.getLabel() + "</h3>"
                    + "</div>"
                    + "<div id=\"draggableContainerTodo\" class=\"panel-body\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\">";

            for (Task task : list.getTaskCollection()) {
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
                                  @RequestParam(value = "idT", required = true) String idTask,
                                  @RequestParam(value = "idL", required = true) String idList) {

        entityManager.moveTask(Integer.parseInt(idTask.substring(13,14)), Integer.parseInt(idList.substring(18,19)));
        
        return board(model);
    }

}
