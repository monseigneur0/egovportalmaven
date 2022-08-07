package egovframework.let.boardSys.service;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BoardSys implements Serializable {


    private Long board_id;
    private String title;
    private String category;
    private Long price;
    private Date insert_date;
    private String keyword;
}
