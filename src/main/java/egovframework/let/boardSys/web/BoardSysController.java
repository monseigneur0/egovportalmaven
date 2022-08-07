package egovframework.let.boardSys.web;

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
    public String createPost(@RequestParam Map<String, Object> map) {

        String boardId = this.boardSysService.create(map);
        return "redirect:/boarddetail.do?boardId=" + boardId;
    }

    @RequestMapping(value = "detailnew.do", method = RequestMethod.GET)
    public String detail(@RequestParam Map<String, Object> map, Model model) {
        Map<String, Object> detailMap = this.boardSysService.detail(map);

        model.addAttribute("detailMap", detailMap);
        return "boardSys/detail";
    }

    @RequestMapping(value = "boarddetail.do", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam Map<String, Object> map) {
        Map<String, Object> detailMap = this.boardSysService.detail(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detailMap);
        String boardId = map.get("boardId").toString();
        mav.addObject("boardId", boardId);
        mav.setViewName("boardSys/detail");
        return mav;
    }

    @RequestMapping(value = "/boardupdate.do", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam Map<String, Object> map) {
        Map<String, Object> detailMap = this.boardSysService.detail(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detailMap);
        mav.setViewName("/boardSys/update");
        return mav;
    }
    @RequestMapping(value = "boardupdate.do", method = RequestMethod.POST)
    public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        boolean isUpdateSuccess = this.boardSysService.edit(map);
        if (isUpdateSuccess) {
            String boardId = map.get("boardId").toString();
            mav.setViewName("redirect:/boarddetail.do?boardId=" + boardId);
        }else {
            mav = this.update(map);
        }

        return mav;
    }

    @RequestMapping(value = "/boarddelete.do", method = RequestMethod.POST)
    public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        boolean isDeleteSuccess = this.boardSysService.remove(map);
        if (isDeleteSuccess) {
            mav.setViewName("redirect:/boardlist.do");
        }else {
            String boardId = map.get("boardId").toString();
            mav.setViewName("redirect:/boarddetail.do?boardId=" + boardId);
        }
        return mav;
    }

    @RequestMapping(value = "boardlist.do")
    public ModelAndView list(@RequestParam Map<String, Object> map) {

        List<Map<String, Object>> list = this.boardSysService.list(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", list);
        if (map.containsKey("keyword")) {
            mav.addObject("keyword", map.get("keyword"));
        }
        mav.setViewName("/boardSys/list");
        return mav;
    }
}
