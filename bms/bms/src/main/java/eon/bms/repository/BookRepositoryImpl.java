package eon.bms.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import eon.bms.domain.Book;
import eon.bms.domain.GenreEnum;
import eon.bms.domain.QBook;
import eon.bms.dto.BookUpdateForm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final static QBook qBook = QBook.book;
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        List<Book> bookList = jpaQueryFactory
                .selectFrom(qBook)
                .fetch();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long bookId) {
        return jpaQueryFactory
                .selectFrom(qBook)
                .where(qBook.id.eq(bookId))
                .fetchOne();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findByName(String keyword, Pageable pageable) {
        List<Book> bookList = jpaQueryFactory
                .selectFrom(qBook)
                .where(qBook.name.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findByAuthor(String keyword, Pageable pageable) {
        List<Book> bookList = jpaQueryFactory
                .selectFrom(qBook)
                .where(qBook.author.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findByYear(String keyword, Pageable pageable) {
        List<Book> bookList = jpaQueryFactory
                .selectFrom(qBook)
                .where(qBook.year.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findByCompany(String keyword, Pageable pageable) {
        List<Book> bookList = jpaQueryFactory
                .selectFrom(qBook)
                .where(qBook.company.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findByGenre(String keyword, Pageable pageable) {
        List<Book> bookList = jpaQueryFactory
                .selectFrom(qBook)
                .where(qBook.genre.eq(GenreEnum.valueOf(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }
    @Override
    @Transactional
    public void deleteBookById(Long id) {
        jpaQueryFactory
                .delete(qBook)
                .where(qBook.id.eq(id))
                .execute();
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        entityManager.persist(book);
        entityManager.flush();
        entityManager.clear();
    }
}
