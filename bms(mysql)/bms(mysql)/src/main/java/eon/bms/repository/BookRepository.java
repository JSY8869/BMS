package eon.bms.repository;

import eon.bms.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookRepository {
    Page<Book> findAll(Pageable pageable);
    Book findById(Long bookId);
    Page<Book> findByName(String keyword, Pageable pageable);
    Page<Book> findByAuthor(String keyword, Pageable pageable);
    Page<Book> findByYear(String keyword, Pageable pageable);
    Page<Book> findByCompany(String keyword, Pageable pageable);
    Page<Book> findByGenre(String keyword, Pageable pageable);

    void deleteBookById(Long id);
    void addBook(Book book);

}
