# **도서 관리 프로그램**

**사용자의 도서 관리를 위한 웹 사이트**
## **어떻게 시작하나요?**
### MySQL
1. JDK 버전이 17인지 확인한다.
    1. File
    2. Project Structure
    3. SDK
2. MySQL이 실행되어 있는지 확인한다.
    1. MySQL 설치
    2. MySQL Workbench 설치
    3. MySQL Workbench 실행
3. application.properties 파일에 들어간다.

4. 아래 정보를 입력한다.
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bms
spring.datasource.username=MySQL아이디
spring.datasource.password=비밀번호
```
5. BmsApplication 파일의 main 함수를 실행한다.
6. 실행을 완료한 후 크롬에 접속한다.
7. 크롬 검색창에 `localhost:8080` 을 입력한다.
### MongoDB
1. JDK 버전이 17인지 확인한다.
    1. File
    2. Project Structure
    3. SDK
2. MongoDB를 설치한다.
3. Application 파일의 main 함수를 실행한다.
4. 실행을 완료한 후 크롬에 접속한다.
5. 크롬 검색창에 `localhost:8080` 을 입력한다.
## **누구랑 만들었나요?**

- [정세영](https://github.com/JSY8869) - 1인 제작

## **API**
### / (GET)
- 기본 홈 페이지
### /register (GET)
- 회원가입 페이지 불러오기
### /register (POST)
- 회원가입 하기
### /login (GET)
- 로그인 페이지 불러오기
### /login (POST)
- 로그인 하기
### /books (GET)
- 도서 목록 페이지 불러오기
### /add (GET)
- 도서 추가 페이지 불러오기
### /add (POST)
- 도서 추가하기
### /{도서 번호}/detail (GET)
- 도서 상세 정보 페이지 불러오기
### /{도서 번호}/edit (GET)
- 도서 수정 페이지 불러오기
### /{도서 번호}/edit (POST)
- 도서 수정하기
### /{도서 번호}/delete (GET)
- 도서 삭제 확인 페이지 불러오기
### /{도서 번호}/delete (POST)
- 도서 삭제하기
### /search (GET)
- 도서 검색 페이지 불러오기
### /search (POST)
- 도서 검색하기

## **라이센스**

This project is licensed under the MIT License/ 이 프로젝트는 MIT 라이센스로 라이센스가 부여되어 있습니다.

## **감사의 말**

- EoN의 미래가 창창하기를...
- 과제가 어려울 수 있지만 완성했을 때 보람이 더 클 것입니다!
