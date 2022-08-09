package egovframework.let.bookrepo.service;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private static final Map<Long, Books> store = new HashMap<>();
    private static long sequence = 0L;

    public Books save(Books books) {

        books.setBook_id(++sequence);
        return books;
    }

    public Books findById(Long id) {
        return store.get(id);
    }

    public List<Books> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long book_id, Books updateParam) {

        Books findItem = findById(book_id);
        findItem.setTitle(updateParam.getTitle());
        findItem.setCategory(updateParam.getCategory());
        findItem.setPrice(updateParam.getPrice());

    }

    public void clearStore() {

        store.clear();
    }


}
