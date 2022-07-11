package ToyProject.BMS.service;

import ToyProject.BMS.model.domain.entity.Book;
import ToyProject.BMS.model.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //도서 검색
    public List<Book> search(String keyword, String category) {
        List<Book> books = new ArrayList<>();
        switch (category) {
            case "NAME":
                books = bookRepository.findByName(keyword);
                break;
            case "AUTHOR":
                books = bookRepository.findByAuthor(keyword);
                break;
            case "YEAR":
                books = bookRepository.findByYear(keyword);
                break;
            case "GENRE":
                books = bookRepository.findByGenre(keyword);
                break;
            case "COMPANY":
                books = bookRepository.findByCompany(keyword);
                break;
        }
        return books;
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
