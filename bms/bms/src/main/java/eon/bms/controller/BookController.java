package eon.bms.controller;

import eon.bms.domain.Book;
import eon.bms.dto.BookSaveForm;
import eon.bms.dto.BookUpdateForm;
import eon.bms.service.BookManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookManagementService bookManagementService;


    // 도서 전체 출력
    @GetMapping("/books")
    public String showAll(Model model, Pageable pageable) {
        Page<Book> books = bookManagementService.printAll(pageable);
        setBookListPage(model, books);
        return "/basic/books";
    }

    // 도서 추가
    @GetMapping("/add")
    public String addForm(@ModelAttribute("book") Book book) {
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
        bookManagementService.plusBook(form);
        model.addAttribute("book", form);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/books";
    }

    // 도서 상세 정보
    @GetMapping("/{bookId}/detail")
    public String bookDetail(@PathVariable("bookId") Long bookId, Model model) {
        model.addAttribute("book", bookManagementService.detailBook(bookId));
        return "/basic/book";
    }

    // 도서 수정
    @GetMapping("/{bookId}/edit")
    public String bookEditForm(@PathVariable("bookId") Long bookId, Model model) {
        model.addAttribute("book", bookManagementService.detailBook(bookId));
        return "/basic/editForm";
    }

    @PostMapping("/{bookId}/edit")
    public String bookEdit(@PathVariable("bookName")String bookName,
                           @Validated @ModelAttribute("book") BookUpdateForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        // 검증 실패시
        if (bindingResult.hasErrors()) {
            return "/basic/editForm";
        }
        // 검증 성공시
        bookManagementService.updateBook(form);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/books/" +
                form.getName()
                + "/detail";
    }

    // 도서 삭제
    @GetMapping("/{bookId}/delete")
    public String bookDeleteForm(@ModelAttribute("book") Book book,
                                 @PathVariable("bookId") Long bookId,
                                 Model model) {
        model.addAttribute("findResult", bookManagementService.detailBook(bookId));
        return "/basic/deleteForm";
    }

    @PostMapping("/{bookId}/delete")
    public String bookDelete(@ModelAttribute Book book) {
        bookManagementService.deleteBook(book.getId());
        return "redirect:/basic/books";
    }

    // 도서 검색
    @GetMapping("/search")
    public String searchBook() {
        return "/basic/searchForm";
    }

    @PostMapping("/search")
    public String findBookListByBookName(@RequestParam("keyword") String keyword,
                                         @RequestParam("category") String category,
                                         BindingResult bindingResult,
                                         Model model,
                                         Pageable pageable) {
        if (bindingResult.hasErrors()) {
            return "/basic/searchForm";
        }
        Page<Book> books = bookManagementService.search(keyword, category, pageable);
        if (books.isEmpty()) {
            model.addAttribute("noResult", true);
        }
        setBookListPage(model, books);
        return "/basic/searchResult";
    }

    private void setBookListPage(Model model, Page<Book> books) {
        int nowPage = books.getPageable().getPageNumber()-1;
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, books.getTotalPages());
        model.addAttribute("books", books);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    }
}
