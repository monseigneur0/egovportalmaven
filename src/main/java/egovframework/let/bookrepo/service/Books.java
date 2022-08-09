package egovframework.let.bookrepo.service;

import lombok.Data;

import java.util.Date;

@Data
public class Books {

    private Long book_id;
    private String title;
    private String category;
    private Long price;
    private Date insert_date;
    private String keyword;

    public Books() {
    }

    public Books(String title,String category,Long price) {
        this.title = title;
        this.category = category;
        this.price = price;

    }
}
