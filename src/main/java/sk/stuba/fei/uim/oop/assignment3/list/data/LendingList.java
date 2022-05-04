package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class LendingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(orphanRemoval = true)
//    private List<ListEntry> bookList;

    @OneToMany
    private List<Book> bookList;

    private boolean lended;

    public LendingList() {
        this.bookList = new ArrayList<>();
    }

}
