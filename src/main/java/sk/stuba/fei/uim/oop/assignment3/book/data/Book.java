package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Author author;

    private int pages;

    private int amount;

    private int lendCount;

    public Book(BookRequest bookRequest){
        this.name = bookRequest.getName();
        this.description = bookRequest.getDescription();
        this.pages = bookRequest.getPages();
        this.amount = bookRequest.getAmount();
        this.lendCount = bookRequest.getLendCount();
    }

}
