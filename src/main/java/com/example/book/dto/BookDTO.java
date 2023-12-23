package com.example.book.dto;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class BookDTO {
  private Long id;
  private String title;
  private String subtitle;
  private String publicationDate; // 문자열로 저장
  private String subject;
  private String publisher;
  private Integer pageCount;

  // 생성자, 게터, 세터 등 필요한 메서드 추가
  // publicationDate를 Date로 변환하는 메서드 추가
  public Date getPublicationDateAsDate() {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      return dateFormat.parse(this.publicationDate);
    } catch (ParseException e) {
      throw new RuntimeException("Failed to convert publicationDate to Date", e);
    }
  }
}
