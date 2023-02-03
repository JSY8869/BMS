package eon.bms.mongo.repository;


import eon.bms.mongo.domain.Book;
import eon.bms.mongo.dto.BookUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookRepository {
    Page<Book> findAll(Pageable pageable);
    Book findById(String bookId);
    Page<Book> findByName(String keyword, Pageable pageable);
    Page<Book> findByAuthor(String keyword, Pageable pageable);
    Page<Book> findByYear(String keyword, Pageable pageable);
    Page<Book> findByCompany(String keyword, Pageable pageable);
    Page<Book> findByGenre(String keyword, Pageable pageable);

    void deleteBookById(String id);
    void addBook(Book book);

    void updateBook(BookUpdateForm bookUpdateForm);

}
