package egovframework.let.boardSys.web;

import egovframework.let.boardSys.service.BoardSys;
import egovframework.let.boardSys.service.BoardSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class BoardSysController {

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

    @RequestMapping(value = "detailnew.do", method = RequestMethod.GET)
    public String detail(BoardSys boardSys, Model model) {
        Map<String, Object> detailMap = this.boardSysService.detail(boardSys);

        model.addAttribute("detailMap", detailMap);
        return "boardSys/detail";
    }

    @RequestMapping(value = "boarddetail.do", method = RequestMethod.GET)
    public ModelAndView detail(BoardSys boardSys) {
        Map<String, Object> detailMap = this.boardSysService.detail(boardSys);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detailMap);
        String boardId = boardSys.getBoard_id().toString();
        mav.addObject("boardId", boardId);
        mav.setViewName("boardSys/detail");
        return mav;
    }

    @RequestMapping(value = "/boardupdate.do", method = RequestMethod.GET)
    public ModelAndView update(BoardSys boardSys) {
        Map<String, Object> detailMap = this.boardSysService.detail(boardSys);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detailMap);
        mav.setViewName("/boardSys/update");
        return mav;
    }
    @RequestMapping(value = "boardupdate.do", method = RequestMethod.POST)
    public ModelAndView updatePost(BoardSys boardSys) {
        ModelAndView mav = new ModelAndView();

        boolean isUpdateSuccess = this.boardSysService.edit(boardSys);
        if (isUpdateSuccess) {
            String boardId = boardSys.getBoard_id().toString();
            mav.setViewName("redirect:/boarddetail.do?boardId=" + boardId);
        }else {
            mav = this.update(boardSys);
        }

        return mav;
    }

    @RequestMapping(value = "/boarddelete.do", method = RequestMethod.POST)
    public ModelAndView deletePost(BoardSys boardSys) {
        ModelAndView mav = new ModelAndView();

        boolean isDeleteSuccess = this.boardSysService.remove(boardSys);
        if (isDeleteSuccess) {
            mav.setViewName("redirect:/boardlist.do");
        }else {
            String boardId = boardSys.getBoard_id().toString();
            mav.setViewName("redirect:/boarddetail.do?boardId=" + boardId);
        }
        return mav;
    }

    @RequestMapping(value = "boardlist.do")
    public ModelAndView list(BoardSys boardSys) {

        List<Map<String, Object>> list = this.boardSysService.list(boardSys);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", list);
        if (!boardSys.getKeyword().equals("")) {
            mav.addObject("keyword", boardSys.getKeyword());
        }
        mav.setViewName("/boardSys/list");
        return mav;
    }
}
