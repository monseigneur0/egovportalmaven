package egovframework.let.boardSys.web;

import egovframework.let.boardSys.service.BoardSysService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(getClass());
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

    @RequestMapping(value = "boarddetail.do", method = RequestMethod.GET)
    public String  detail(@RequestParam Map<String, Object> map, Model model) {
        Map<String, Object> detailMap = this.boardSysService.detail(map);
        log.info(String.valueOf(model));
        log.debug(String.valueOf(detailMap));

        String boardId = map.get("boardId").toString();
        model.addAttribute("boardId", boardId);
        model.addAttribute("data",detailMap);
        return "boardSys/detail";
    }

    @RequestMapping(value = "/boardupdate.do", method = RequestMethod.GET)
    public String update(@RequestParam Map<String, Object> map,Model model) {
        Map<String, Object> detailMap = this.boardSysService.detail(map);

        model.addAttribute("data", detailMap);
        return "/boardSys/update";
    }
    @RequestMapping(value = "boardupdate.do", method = RequestMethod.POST)
    public String updatePost(@RequestParam Map<String, Object> map, Model model) {

        boolean isUpdateSuccess = this.boardSysService.edit(map);
        log.info( "this.boardSysService.edit(map) = "+this.boardSysService.edit(map));
        System.out.println("this.boardSysService.edit(map) = " + this.boardSysService.edit(map));
        if (isUpdateSuccess) {
            String boardId = map.get("boardId").toString();
            return "redirect:/boarddetail.do?boardId=" + boardId;
        }else {
            return "redirect:/boardupdate.do";
        }

    }

    @RequestMapping(value = "/boarddelete.do", method = RequestMethod.POST)
    public String deletePost(@RequestParam Map<String, Object> map, Model model) {

        boolean isDeleteSuccess = this.boardSysService.remove(map);
        if (isDeleteSuccess) {
            return "redirect:/boardlist.do";
        }else {
            String boardId = map.get("boardId").toString();
            return "redirect:/boarddetail.do?boardId=" + boardId;
        }
    }

    @RequestMapping(value = "boardlist.do")
    public String list(@RequestParam Map<String, Object> map,Model model) {
        List<Map<String, Object>> list = this.boardSysService.list(map);
        model.addAttribute("data",list);
        return "/boardSys/list";
    }
}
