package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Getter
@Setter
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private java.util.List<Book> lendingList;

    private boolean lended;

    public List() {
        this.lendingList = new ArrayList<>();
    }

}
