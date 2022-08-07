package egovframework.let.bookrepo.web;

import egovframework.let.bookrepo.service.BookRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/repo")
public class BookRepoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BookRepoService bookRepoService;

    @RequestMapping(value="/create.do", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("book/create");
    }

    @RequestMapping(value = "/create.do", method = RequestMethod.POST)
    public ModelAndView createPost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        String bookId = this.bookRepoService.create(map);
        if (bookId == null) {
            mav.setViewName("redirect:/create.do");
        }else {
            mav.setViewName("redirect:/detail.do?bookId=" + bookId);
        }

        return mav;
    }

    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam Map<String, Object> map) {
        log.debug("==========================");
        log.debug(String.valueOf(map));
        Map<String, Object> detailMap = this.bookRepoService.detail(map);
        log.info(String.valueOf(detailMap));

        ModelAndView mav = new ModelAndView();
        log.info(String.valueOf(mav));
        mav.addObject("data", detailMap);
        String bookId = map.get("bookId").toString();
        mav.addObject("bookId", bookId);
        mav.setViewName("/book/detail");
        return mav;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam Map<String, Object> map) {
        Map<String, Object> detailMap = this.bookRepoService.detail(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", detailMap);
        mav.setViewName("/book/update");
        return mav;
    }
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        boolean isUpdateSuccess = this.bookRepoService.edit(map);
        if (isUpdateSuccess) {
            String bookId = map.get("bookId").toString();
            mav.setViewName("redirect:/detail?bookId=" + bookId);
        }else {
            mav = this.update(map);
        }

        return mav;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();

        boolean isDeleteSuccess = this.bookRepoService.remove(map);
        if (isDeleteSuccess) {
            mav.setViewName("redirect:/list.do");
        }else {
            String bookId = map.get("bookId").toString();
            mav.setViewName("redirect:/detail.do?bookId=" + bookId);
        }
        return mav;
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(@RequestParam Map<String, Object> map) {

        List<Map<String, Object>> list = this.bookRepoService.list(map);

        ModelAndView mav = new ModelAndView();
        mav.addObject("data", list);
        if (map.containsKey("keyword")) {
            mav.addObject("keyword", map.get("keyword"));
        }
        mav.setViewName("/book/list");
        return mav;
    }
}
