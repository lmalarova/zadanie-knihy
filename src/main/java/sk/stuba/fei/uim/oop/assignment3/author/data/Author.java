package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @OneToMany(orphanRemoval = true)
    private List<Book> books;

    public Author(AuthorRequest request){
        this.name = request.getName();
        this.surname = request.getSurname();
    }
}
