package ToyProject.BMS.model.repository;

import ToyProject.BMS.model.domain.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public void addBook(Book book) {
        em.persist(book);
        em.flush();
        em.clear();
    }

    public void deleteByBook(Long id) {
        em.createQuery("delete Book where id = :id").setParameter("id", id);
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findByName(String name) {
        return em.createQuery("select b from Book b where b.name = :name").setParameter("name", name).getResultList();
    }

    public List<Book> findByAuthor(String author) {
        return em.createQuery("select b from Book b where b.author = :author").setParameter("author", author).getResultList();
    }

    public List<Book> findByYear(String year) {
        return em.createQuery("select b from Book b where b.year = :year").setParameter("year", year).getResultList();
    }

    public List<Book> findByGenre(String genre) {
        return em.createQuery("select b from Book b where b.genre = :genre").setParameter("genre", genre).getResultList();
    }

    public List<Book> findByCompany(String company) {
        return em.createQuery("select b from Book b where b.company = :company").setParameter("company", company).getResultList();
    }

    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    public void updateBook(Long bookId, Book updateParam) {
        Book book = findOne(bookId);
        book.setName(updateParam.getName());
        book.setAuthor(updateParam.getAuthor());
        book.setCompany(updateParam.getCompany());
        book.setGenre(updateParam.getGenre());
        book.setYear(updateParam.getYear());
        em.persist(book);
        em.flush();
        em.clear();
    }
}
