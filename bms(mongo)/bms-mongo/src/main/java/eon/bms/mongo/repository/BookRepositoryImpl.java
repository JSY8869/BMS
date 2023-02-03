package eon.bms.mongo.repository;

import eon.bms.mongo.domain.Book;
import eon.bms.mongo.dto.BookUpdateForm;
import eon.bms.mongo.exception.CustomException;
import eon.bms.mongo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{

    private final BookMongoRepository bookMongoRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookMongoRepository.findAll(pageable);
    }

    @Override
    public Book findById(String bookId) {
        return bookMongoRepository.findById(String.valueOf(bookId))
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    }

    @Override
    public Page<Book> findByName(String keyword, Pageable pageable) {
        return bookMongoRepository.findByName(keyword, pageable);
    }

    @Override
    public Page<Book> findByAuthor(String keyword, Pageable pageable) {
        return bookMongoRepository.findByAuthor(keyword, pageable);
    }

    @Override
    public Page<Book> findByYear(String keyword, Pageable pageable) {
        return bookMongoRepository.findByYear(keyword, pageable);
    }

    @Override
    public Page<Book> findByCompany(String keyword, Pageable pageable) {
        return bookMongoRepository.findByCompany(keyword, pageable);
    }

    @Override
    public Page<Book> findByGenre(String keyword, Pageable pageable) {
        return bookMongoRepository.findByGenre(keyword, pageable);
    }

    @Override
    public void deleteBookById(String id) {
        bookMongoRepository.deleteById(String.valueOf(id));
    }

    @Override
    public void addBook(Book book) {
        bookMongoRepository.save(book);
    }

    @Override
    public void updateBook(BookUpdateForm bookUpdateForm) {
        Book book = bookMongoRepository.findById(bookUpdateForm.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        book.updateBook(bookUpdateForm);
        bookMongoRepository.save(book);
    }
}
