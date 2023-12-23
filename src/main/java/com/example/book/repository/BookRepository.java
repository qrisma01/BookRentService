package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받아 기본적인 CRUD 메서드를 제공받는 BookRepository 인터페이스
public interface BookRepository extends JpaRepository<Book, Long> {
  // 특별한 메서드가 필요하다면 여기에 추가
}