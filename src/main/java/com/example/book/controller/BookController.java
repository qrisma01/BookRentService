package com.example.book.controller;

import com.example.book.dto.BookDTO;
import com.example.book.entity.Book;
import com.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
// 웹 요청을 처리하는 BookController 클래스
public class BookController {

  private final BookService bookService;

  @Autowired
  // BookService를 주입받는 생성자
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  // 모든 책 조회
  @GetMapping
  public String getAllBooks(Model model, Pageable pageable) {
    Page<Book> books = bookService.getAllBooks(pageable);
    model.addAttribute("books", books);
    model.addAttribute("currentPage", pageable.getPageNumber());
    model.addAttribute("totalPages", books.getTotalPages());
    return "book/list"; // Thymeleaf 템플릿 이름 반환
  }

  // 책 등록 폼 표시
  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("book", new Book());
    return "book/create"; // Thymeleaf 템플릿 이름 반환
  }

  // 책 등록 처리
  @PostMapping("/create")
  public String createBook(BookDTO bookDTO) {
    // DTO를 엔터티로 변환
    Book book = convertDTOToEntity(bookDTO);

    // 서비스를 통해 엔터티 저장
    bookService.saveBook(bookDTO);

    return "redirect:/books"; // 목록 페이지로 리다이렉트
  }
  // 책 등록 다른 버전
  @PostMapping("/create1")
  public String createBook1(@ModelAttribute("book") BookDTO bookDTO) {
    // DTO를 엔터티로 변환
//    Book book = convertDTOToEntity(bookDTO);

    // 서비스를 통해 엔터티 저장
    bookService.saveBook(bookDTO);

    return "redirect:/books"; // 목록 페이지로 리다이렉트
  }
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    // id를 사용하여 책 정보를 가져와서 모델에 추가
    Book book = bookService.getBookById(id);
    model.addAttribute("book", book);
    return "book/book-edit"; // 수정 화면으로 이동하는 Thymeleaf 템플릿 이름
  }

  @PostMapping("/update")
  public String updateBook(@ModelAttribute("book") BookDTO bookDTO) {
    // bookDTO를 이용하여 책 정보를 업데이트하는 로직
    bookService.saveBook(bookDTO);
    return "redirect:/books"; // 목록 페이지로 리다이렉트
  }

  @PostMapping("/delete/{id}")
  public String deleteBookByPost(@PathVariable Long id) {
    // id를 사용하여 책을 삭제하는 로직
    bookService.deleteBook(id);
    return "redirect:/books"; // 목록 페이지로 리다이렉트
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBookByDelete(@PathVariable Long id) {
    try {
      // id를 사용하여 책을 삭제하는 로직
      bookService.deleteBook(id);

      // 삭제 성공시 응답
      return new ResponseEntity<>("책이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    } catch (Exception e) {
      // 삭제 실패시 응답
      return new ResponseEntity<>("책 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Book convertDTOToEntity(BookDTO bookDTO) {
    Book book = new Book();
    book.setTitle(bookDTO.getTitle());
    book.setSubtitle(bookDTO.getSubtitle());
    book.setPublicationDate(bookDTO.getPublicationDateAsDate()); // 변경된 부분
    book.setSubject(bookDTO.getSubject());
    book.setPublisher(bookDTO.getPublisher());
    book.setPageCount(bookDTO.getPageCount());

    return book;
  }
  // 다른 CRUD 메서드들 (책 수정, 삭제) 추가 가능
}