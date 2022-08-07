package egovframework.let.boardSys.service.impl;

import egovframework.let.boardSys.service.BoardSys;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardSysDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public int insert(BoardSys boardSys) {
        return this.sqlSessionTemplate.insert("board.insert", boardSys);
    }

    public Map<String, Object> selectDetail(BoardSys boardSys) {
        return this.sqlSessionTemplate.selectOne("board.select_detail", boardSys);
    }

    public int update(BoardSys boardSys) {
        return this.sqlSessionTemplate.update("board.update", boardSys);
    }
    public int delete(BoardSys boardSys) {
        return this.sqlSessionTemplate.delete("board.delete", boardSys);
    }
    public List<Map<String, Object>> selectList(BoardSys boardSys) {
        return this.sqlSessionTemplate.selectList("board.select_list", boardSys);
    }

}
