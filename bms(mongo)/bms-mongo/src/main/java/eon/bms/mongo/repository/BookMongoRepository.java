package eon.bms.mongo.repository;

import eon.bms.mongo.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookMongoRepository extends MongoRepository<Book, String> {
    Page<Book> findByName(String keyword, Pageable pageable);
    Page<Book> findByAuthor(String keyword, Pageable pageable);
    Page<Book> findByGenre(String keyword, Pageable pageable);
    Page<Book> findByYear(String keyword, Pageable pageable);
    Page<Book> findByCompany(String keyword, Pageable pageable);
}
