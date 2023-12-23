package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
// 책 비즈니스 로직을 처리하는 BookService 클래스
public class BookService {

  private final BookRepository bookRepository;

  @Autowired
  // BookRepository를 주입받는 생성자
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  // 모든 책 조회
  public Page<Book> getAllBooks(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }
  // 특정 ID의 책 조회
  public Book getBookById(Long id) {
    return bookRepository.findById(id).orElse(null);
  }

  // 책 저장
//  public void saveBook(Book book) {
//    bookRepository.save(book);
//  }

  public void saveBook(BookDTO bookDTO) {
    Long id = bookDTO.getId();

    if (id != null) {
      // 책 ID가 존재하면 업데이트
      Book existingBook = getBookById(id);
      mapBookDTOToBook(bookDTO, existingBook);
      bookRepository.save(existingBook);
    } else {
      // 책 ID가 없으면 신규 등록
      Book newBook = new Book();
      mapBookDTOToBook(bookDTO, newBook);
      bookRepository.save(newBook);
    }
  }

  private void mapBookDTOToBook(BookDTO bookDTO, Book book) {
    // DTO에서 엔터티로의 매핑 로직 작성
    book.setTitle(bookDTO.getTitle());
    book.setSubtitle(bookDTO.getSubtitle());

    // publicationDate 문자열을 Date로 변환
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date parsedDate = dateFormat.parse(bookDTO.getPublicationDate());
      book.setPublicationDate(parsedDate);
    } catch (ParseException e) {
      // 파싱 오류 처리
      e.printStackTrace();
    }

    book.setSubject(bookDTO.getSubject());
    book.setPublisher(bookDTO.getPublisher());
    book.setPageCount(bookDTO.getPageCount());
    // ... 기타 필요한 매핑 작업 ...
  }
  // 특정 ID의 책 삭제
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }
  /*
  public void updateBook(BookDTO bookDTO) {
    Book existingBook = getBookById(bookDTO.getId());

    // 업데이트 로직 작성 (예시: 일부 필드만 업데이트)
    existingBook.setTitle(bookDTO.getTitle());
    existingBook.setSubtitle(bookDTO.getSubtitle());
    existingBook.setPublicationDate(bookDTO.getPublicationDate());
    existingBook.setSubject(bookDTO.getSubject());
    existingBook.setPublisher(bookDTO.getPublisher());
    existingBook.setPageCount(bookDTO.getPageCount());

    bookRepository.save(existingBook);
  }

  public void deleteBookById(Long id) {
    Book bookToDelete = getBookById(id);
    bookRepository.delete(bookToDelete);
  }
  */
}