# BMS

Book Management System

## 기원
```
도서관리 프로그램을 만들기로 한다. 구현할 기능은 다음과 같다

도서 추가(도서명, 저자, 출판연도, 출판사명, 장르 입력)
도서 검색 2-1. 도서명, 저자, 출판연도, 출판사명, 장르 로 개별적 검색가능)
도서 정보 수정
도서 삭제
현재 총 도서 목록 출력( 도서명, 저자, 출판일, 출판사명, 장르 출력)
저장(읽어들인 입력파일에 저장한다.)
프로그램 나가기(자동저장)
주의사항
input.txt 파일 이외의 다른 파일을 사용하거나 만들지 않는다.
객체지향 언어로 코딩할 것(사람, 도서DB, 도서관리 system 등의 객체로 이루어져야 함)
객체를 추가해도 되나 사람, 도서DB, 도서관리 system 등의 객체는 별도의 파일로 만들어 서로 import할 것
```
- 위와 같은 내용의 E-oN 동아리 과제를 Spring, Thymeleaf 를 활용하여 서비스가 가능한 웹 페이지의 형태로 개발한다.

## 기능
1. 도서 추가 페이지
2. 도서 검색 페이지 (원하는 분류별 검색 가능)
3. 도서 정보 수정 페이지
4. 도서 삭제 페이지
5. 도서 목록 페이지
6. 도서 상세 정보 페이지

## 사용 기술
- Spring Boot 2.7.1
- thymeleaf 3.0.15
- java 11
- spring data jpa


merge 정보
- spring web

## DB
h2 database

## url Mapping
### "/basic/books"
- Get
  - 도서 전체 출력 창
  
### "/basic/books/add"
- Get
  - 도서 추가 화면으로 이동("basic/addForm")
- Post
  - 도서 추가 및 redirect 이동("/basic/books/{bookId}")
  
### "/basic/books/{bookName}"
- Get
  - 도서 제목 검색
  
### "/basic/books/{bookAuthor}"
- Get
  - 도서 저자 검색
  
### "/basic/books/{bookYear}"
- Get
  - 도서 출판연도 검색
  
### "/basic/books/{bookCompany}"
- Get
  - 도서 출판사명 검색
  
### "/basic/books/{bookGenre}"
- Get
  - 도서 장르별 검색

### "/basic/books/{bookId}"
- Get
  - 도서 상세 정보
  - "basic/item" 으로 출력

### "/basic/books/{bookId}/edit"
- Get
  - 도서 수정 화면
  - 도서 상세 정보 화면에서 수정 버튼을 눌러 이동
- Post
  - 도서 수정 화면에서 입력한 정보에 따라 도서 수정
  - redirect로 "/basic/books/{bookId}"(도서 상세 정보) 화면으로 이동

### "/basic/books/{bookId}/delete"
- Post
  - 도서 상세 정보 화면에서 삭제 버튼을 눌러 작동
  - 삭제 완료 후 redirect로 도서 전체 출력 화면으로 이동

info 추가
