package egovframework.let.bookrepo.service.impl;

import egovframework.let.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookRepoServiceImpl implements BookService {

    @Autowired
    BookRepoDAO bookDao;

    @Override
    public String create(Map<String, Object> map) {
        int affectRowCount = this.bookDao.insert(map);
        if (affectRowCount ==  1) {
            return map.get("book_id").toString();
        }
        return null;
    }
    @Override
    public Map<String, Object> detail(Map<String, Object> map){
        return this.bookDao.selectDetail(map);
    }
    @Override
    public boolean edit(Map<String, Object> map) {
        int affectRowCount = this.bookDao.update(map);
        return affectRowCount == 1; //1개 행 영향 받은지 검사
    }
    @Override
    public boolean remove(Map<String, Object> map) {
        int affectRowCount = this.bookDao.delete(map);
        return affectRowCount == 1;
    }
    @Override
    public List<Map<String, Object>> list(Map<String, Object> map){
        return this.bookDao.selectList(map);
    }

}
