package egovframework.let.boardSys.service.impl;

import egovframework.let.boardSys.service.BoardSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardSysImpl implements BoardSysService {

    @Autowired
    BoardSysDao boardSysDao;

    @Override
    public String create(Map<String, Object> map) {
        int affectRowCount = this.boardSysDao.insert(map);
        if (affectRowCount == 1) {
            return map.get("board_id").toString();
        }
        return null;
    }

    @Override
    public Map<String, Object> detail(Map<String, Object> map) {
        return this.boardSysDao.selectDetail(map);
    }

    @Override
    public boolean edit(Map<String, Object> map) {
        int affectRowCount = this.boardSysDao.update(map);
        return affectRowCount == 1; //1개 행 영향 받은지 검사
    }
    @Override
    public boolean remove(Map<String, Object> map) {
        int affectRowCount = this.boardSysDao.delete(map);
        return affectRowCount == 1;
    }
    @Override
    public List<Map<String, Object>> list(Map<String, Object> map){
        return this.boardSysDao.selectList(map);
    }

}
