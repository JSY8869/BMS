package eon.bms.service;

import eon.bms.domain.Book;
import eon.bms.dto.BookSaveForm;
import eon.bms.dto.BookUpdateForm;
import eon.bms.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookManagementService {

    private final BookRepository bookRepository;

    // 도서 추가
    public void plusBook(BookSaveForm form) {
        bookRepository.addBook(form.toBook());
    }

    // 도서 삭제
    public void deleteBook(Long id) {
        bookRepository.deleteBookById(id);
    }

    //도서 검색
    public Page<Book> search(String keyword, String category, Pageable pageable) {
        Page<Book> books = null;
        switch (category) {
            case "NAME":
                books = bookRepository.findByName(keyword, pageable);
                break;
            case "AUTHOR":
                books = bookRepository.findByAuthor(keyword, pageable);
                break;
            case "YEAR":
                books = bookRepository.findByYear(keyword, pageable);
                break;
            case "GENRE":
                books = bookRepository.findByGenre(keyword, pageable);
                break;
            case "COMPANY":
                books = bookRepository.findByCompany(keyword, pageable);
                break;
        }
        return books;
    }

    // 도서 전체 출력
    public Page<Book> printAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // 도서 수정
    public void updateBook(BookUpdateForm form) {
        Book book = bookRepository.findById(form.getId());
        book.
    }

    // 도서 상세 정보
    public Book detailBook(Long bookId) {
        return bookRepository.findById(bookId);
    }
}
