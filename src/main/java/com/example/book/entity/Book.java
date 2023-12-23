package com.example.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
// JPA 엔터티로 사용되는 Book 클래스
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // 자동 생성되는 고유 식별자
  private Long id;

  private String title;
  private String subtitle;
  private Date publicationDate;
  private String subject;
  private String publisher;
  private Integer pageCount;

  public Book() {
    // 기본 생성자
  }

  // 생성자를 통한 초기화
  public Book(String title, String subtitle, Date publicationDate, String subject, String publisher, Integer pageCount) {
    this.title = title;
    this.subtitle = subtitle;
    this.publicationDate = publicationDate;
    this.subject = subject;
    this.publisher = publisher;
    this.pageCount = pageCount;
  }

  // 게터와 세터 메서드들

  // ID 값 조회
  public Long getId() {
    return id;
  }

  // ID 값 설정
  public void setId(Long id) {
    this.id = id;
  }

  // 제목 조회
  public String getTitle() {
    return title;
  }

  // 제목 설정
  public void setTitle(String title) {
    this.title = title;
  }

  // 부제목 조회
  public String getSubtitle() {
    return subtitle;
  }

  // 부제목 설정
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  // 출판일 조회
  public Date getPublicationDate() {
    return publicationDate;
  }

  // 출판일 설정
  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  // 주제 조회
  public String getSubject() {
    return subject;
  }

  // 주제 설정
  public void setSubject(String subject) {
    this.subject = subject;
  }

  // 출판사 조회
  public String getPublisher() {
    return publisher;
  }

  // 출판사 설정
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  // 페이지 수 조회
  public Integer getPageCount() {
    return pageCount;
  }

  // 페이지 수 설정
  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }
}
