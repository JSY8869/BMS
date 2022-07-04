package ToyProject.BMS.service;

import ToyProject.BMS.model.domain.entity.Book;
import ToyProject.BMS.model.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookManagementService {

    private final BookRepository bookRepository;

    // 도서 추가
    public Long plusBook(Book book) {
        bookRepository.addBook(book);
        return book.getId();
    }

    // 도서 삭제
    public void deleteBook(Long id) {
        bookRepository.deleteByBook(id);
    }

    // 도서 저자 검색
    public List<Book> searchBookAuthor(String author) {
        return bookRepository.findByName(author);
    }

    // 도서 출판사 검색
    public List<Book> searchBookCompany(String company) {
        return bookRepository.findByName(company);
    }

    // 도서 출판연도 검색
    public List<Book> searchBookYear(String year) {
        return bookRepository.findByName(year);
    }

    // 도서 장르 검색
    public List<Book> searchBookGenre(String genre) {
        return bookRepository.findByName(genre);
    }

    // 도서 전체 출력
    public List<Book> printAll() {
        return bookRepository.findAll();
    }

    // 도서 수정
    public void updateBook(Long id, Book book) {
        bookRepository.updateBook(id, book);
    }

    // 도서 상세 정보
    public Book detailBook(Long id) {
        return bookRepository.findOne(id);
    }
}
