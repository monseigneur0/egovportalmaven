package egovframework.let.boardSys.service.impl;

import egovframework.let.boardSys.service.BoardSys;
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
    public String create(BoardSys boardSys) {
        int affectRowCount = this.boardSysDao.insert(boardSys);
        if (affectRowCount == 1) {
            return boardSys.getBoard_id().toString();
        }
        return null;
    }

    @Override
    public Map<String, Object> detail(BoardSys boardSys) {
        return this.boardSysDao.selectDetail(boardSys);
    }

    @Override
    public boolean edit(BoardSys boardSys) {
        int affectRowCount = this.boardSysDao.update(boardSys);
        return affectRowCount == 1; //1개 행 영향 받은지 검사
    }
    @Override
    public boolean remove(BoardSys boardSys) {
        int affectRowCount = this.boardSysDao.delete(boardSys);
        return affectRowCount == 1;
    }
    @Override
    public List<Map<String, Object>> list(BoardSys boardSys){
        return this.boardSysDao.selectList(boardSys);
    }

}
