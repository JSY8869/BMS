package ToyProject.BMS.model.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String name, author, year, genre, company;

    public Book() {
    }

    public Book(String name, String author, String year, String genre, String company) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.company = company;
    }
}
