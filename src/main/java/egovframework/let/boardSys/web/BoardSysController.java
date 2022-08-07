package egovframework.let.boardSys.web;

import egovframework.let.boardSys.service.BoardSys;
import egovframework.let.boardSys.service.BoardSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BoardSysController {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    BoardSysService boardSysService;

    @RequestMapping(value = "boardcreate.do", method = RequestMethod.GET)
    public String create() {
        return "boardSys/create";
    }

    @RequestMapping(value = "boardcreate.do", method = RequestMethod.POST)
    public String createPost(BoardSys boardSys) {

        String boardId = this.boardSysService.create(boardSys);
        return "redirect:/boarddetail.do?boardId=" + boardId;
    }

    @RequestMapping(value = "boarddetail.do", method = RequestMethod.GET)
    public String detail(BoardSys boardSys, Model model,String boardId) {
        log.debug("==========================");
        log.debug(boardId);
        log.debug(String.valueOf(boardSys));
        log.info(String.valueOf(model));
        boardSys.setBoard_id(Long.valueOf(boardId));
        log.debug(String.valueOf(boardSys));
        Map<String, Object> detailMap = this.boardSysService.detail(boardSys);

        log.debug(String.valueOf(detailMap)+"================");

        model.addAttribute("boardId", boardId);
        model.addAttribute("data",detailMap);
        return "boardSys/detail";
    }

    @RequestMapping(value = "/boardupdate.do", method = RequestMethod.GET)
    public String update(BoardSys boardSys,Model model,String boardId) {
        boardSys.setBoard_id(Long.valueOf(boardId));
        Map<String, Object> detailMap = this.boardSysService.detail(boardSys);

        model.addAttribute("data", detailMap);
        return "/boardSys/update";
    }
    @RequestMapping(value = "boardupdate.do", method = RequestMethod.POST)
    public String updatePost(BoardSys boardSys, Model model) {

        boolean isUpdateSuccess = this.boardSysService.edit(boardSys);
        log.info( "this.boardSysService.edit(map) = "+this.boardSysService.edit(boardSys));
        System.out.println("this.boardSysService.edit(map) = " + this.boardSysService.edit(boardSys));
        if (isUpdateSuccess) {
            String boardId = model.getAttribute("board_id").toString();
            return "redirect:/boarddetail.do?boardId=" + boardId;
        }else {
            return "redirect:/boardupdate.do";
        }

    }

    @RequestMapping(value = "/boarddelete.do", method = RequestMethod.POST)
    public String deletePost(BoardSys boardSys, Model model,String boardId) {
        model.addAttribute("boardId", boardId);
        boardSys.setBoard_id(Long.valueOf(boardId));
        Map<String, Object> detailMap = this.boardSysService.detail(boardSys);

        BoardSys boardSys1 = (BoardSys) this.boardSysService.detail(boardSys);
        log.info(String.valueOf(boardSys1));
        boolean isDeleteSuccess = this.boardSysService.remove(boardSys1);
        if (isDeleteSuccess) {
            return "redirect:/boardlist.do";
        }else {
            String board_Id = model.getAttribute("board_id").toString();
            return "redirect:/boarddetail.do?boardId=" + board_Id;
        }
    }

    @RequestMapping(value = "boardlist.do")
    public String list(BoardSys boardSys,Model model) {
        List<Map<String, Object>> list = this.boardSysService.list(boardSys);
        model.addAttribute("data",list);
        return "/boardSys/list";
    }
}
