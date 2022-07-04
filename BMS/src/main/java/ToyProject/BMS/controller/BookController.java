package ToyProject.BMS.controller;

import ToyProject.BMS.model.domain.entity.Book;
import ToyProject.BMS.service.BookManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/books")
@Slf4j
public class BookController {

    private final BookManagementService bookManagementService;

    @PostConstruct
    public void init() {
        bookManagementService.plusBook(new Book("정세영", "2", "2", "2", "2"));
        bookManagementService.plusBook(new Book("정세영2", "2", "2", "2", "2"));
    }

    // 도서 전체 출력
    @GetMapping
    public String showAll(Model model) {
        List<Book> books = bookManagementService.printAll();
        model.addAttribute("books", books);
        return "/basic/books";
    }

    // 도서 추가
    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String addBook(Book book, Model model, RedirectAttributes redirectAttributes) {
        bookManagementService.plusBook(book);
        model.addAttribute("book", book);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/books/"+book.getId();
    }

    // 도서 상세 정보
    @GetMapping("/{bookId}")
    public String bookDetail(@PathVariable("bookId")Long id, Model model) {
        Book book = bookManagementService.detailBook(id);
        model.addAttribute("book", book);
        return "/basic/book";
    }

    // 도서 수정
    @GetMapping("/{bookId}/edit")
    public String bookEditForm(@PathVariable("bookId")Long id, Model model) {
        model.addAttribute("book", bookManagementService.detailBook(id));
        return "/basic/editForm";
    }

    @PostMapping("/{bookId}/edit")
    public String bookEdit(@PathVariable("bookId")Long id, Book book, Model model, RedirectAttributes redirectAttributes) {
        bookManagementService.updateBook(id, book);
        model.addAttribute("book", book);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/books/" + id;
    }

    // 도서 삭제
    @GetMapping("/{bookId}/delete")
    public String bookDeleteForm(@PathVariable("bookId") Long id, Model model) {
        model.addAttribute("book", bookManagementService.detailBook(id));
        return "/basic/deleteForm";
    }

    @PostMapping("/{bookId}/delete")
    public String bookDelete(@PathVariable("bookId") Long id, Model model) {
        bookManagementService.deleteBook(id);
        return "redirect:/basic/books";
    }
}
