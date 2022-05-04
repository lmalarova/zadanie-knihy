package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;

@Data
@Entity
public class ListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Book book;

    public ListEntry() {

    }

    public ListEntry(Book book) {
        this.book = book;
    }
}
