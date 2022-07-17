package ToyProject.BMS.controller;

import ToyProject.BMS.controller.form.BookSaveForm;
import ToyProject.BMS.controller.form.BookUpdateForm;
import ToyProject.BMS.model.domain.entity.Book;
import ToyProject.BMS.model.domain.entity.SearchCategory;
import ToyProject.BMS.model.domain.service.BookManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String addBook(@Validated @ModelAttribute("book") BookSaveForm form, BindingResult bindingResult,
                          Model model, RedirectAttributes redirectAttributes) {
        // 검증 실패시
        if (bindingResult.hasErrors()) {
            return "/basic/addForm";
        }
        // 검증 성공시
        Book book = new Book(form.getName(), form.getAuthor(), form.getYear(), form.getGenre(), form.getCompany());
        bookManagementService.plusBook(book);
        model.addAttribute("book", book);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/books/"+book.getId()+"/detail";
    }

    // 도서 상세 정보
    @GetMapping("/{bookId}/detail")
    public String bookDetail(@PathVariable("bookId") Long id, Model model) {
        Book book = bookManagementService.detailBook(id);
        model.addAttribute("book", book);
        return "/basic/book";
    }

    // 도서 수정
    @GetMapping("/{bookId}/edit")
    public String bookEditForm(@PathVariable("bookId") Long id, Model model) {
        Book book = bookManagementService.detailBook(id);
        model.addAttribute("book", book);
        return "/basic/editForm";
    }

    @PostMapping("/{bookId}/edit")
    public String bookEdit(@PathVariable("bookId")Long id,
                           @Validated @ModelAttribute("book") BookUpdateForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        // 검증 실패시
        if (bindingResult.hasErrors()) {
            return "/basic/editForm";
        }
        // 검증 성공시
        Book book = new Book();
        book.setName(form.getName());
        book.setYear(form.getYear());
        book.setCompany(form.getCompany());
        book.setAuthor(form.getAuthor());
        book.setGenre(form.getGenre());
        bookManagementService.updateBook(id, book);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/books/" + id + "/detail";
    }

    // 도서 삭제
    @GetMapping("/{bookId}/delete")
    public String bookDeleteForm(@PathVariable("bookId") Long id, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("findResult", bookManagementService.detailBook(id));
        return "/basic/deleteForm";
    }

    @PostMapping("/{bookId}/delete")
    public String bookDelete(@ModelAttribute Book book, Model model) {
        bookManagementService.deleteBook(book.getId());
        return "redirect:/basic/books";
    }

    // 도서 검색
    @GetMapping("/search")
    public String searchBook(Model model) {
        model.addAttribute("keyword", "");
        model.addAttribute("category", makeCategory());
        model.addAttribute("searchCategory", new SearchCategory());
        return "/basic/searchForm";
    }

    @PostMapping("/search")
    public String findBookListByBookName(@Validated @ModelAttribute SearchCategory searchCategory,
                                         BindingResult bindingResult,
                                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", makeCategory());
            return "/basic/searchForm";
        }
        List<Book> bookList = bookManagementService.search(searchCategory.getKeyword(), searchCategory.getCategory());
        if (bookList.isEmpty()) {
            model.addAttribute("noResult", true);
        }
        model.addAttribute("books", bookList);
        return "/basic/searchResult";
    }

    private List<SearchCategory> makeCategory() {
        List<SearchCategory> category = new ArrayList<>();
        category.add(new SearchCategory("NAME", "제목"));
        category.add(new SearchCategory("AUTHOR", "저자"));
        category.add(new SearchCategory("YEAR", "출판연도"));
        category.add(new SearchCategory("GENRE", "장르"));
        category.add(new SearchCategory("COMPANY", "출판사명"));
        return category;
    }
}
