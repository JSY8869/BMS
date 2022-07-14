package ToyProject.BMS.model.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Long id;

    private String name, author, year, genre, company;

    public Book(String name, String author, String year, String genre, String company) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.company = company;
    }
}
