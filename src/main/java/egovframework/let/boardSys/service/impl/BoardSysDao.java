package egovframework.let.boardSys.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardSysDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public int insert(Map<String, Object> map) {
        return this.sqlSessionTemplate.insert("board.insert", map);
    }

    public Map<String, Object> selectDetail(Map<String, Object> map) {
        return this.sqlSessionTemplate.selectOne("board.select_detail", map);
    }

    public int update(Map<String, Object> map) {
        return this.sqlSessionTemplate.update("board.update", map);
    }
    public int delete(Map<String, Object> map) {
        return this.sqlSessionTemplate.delete("board.delete", map);
    }
    public List<Map<String, Object>> selectList(Map<String, Object> map) {
        return this.sqlSessionTemplate.selectList("board.select_list", map);
    }

}
