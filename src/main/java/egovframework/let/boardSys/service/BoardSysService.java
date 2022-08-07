package egovframework.let.boardSys.service;

import java.util.List;
import java.util.Map;

public interface BoardSysService {

    String create(BoardSys boardSys);

    Map<String, Object> detail(BoardSys boardSys);


    boolean edit(BoardSys boardSys);

    boolean remove(BoardSys boardSys);

    List<Map<String, Object>> list(BoardSys boardSys);
}
