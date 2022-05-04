package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorResponse {
    private final Long id;
    private final String name;
    private final String surname;
    private final List<Long> books;

    public AuthorResponse(Author author){
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.books = new ArrayList<>();
        this.createBooksList(author);
    }

    public void createBooksList(Author author){
        if(author.getBooks() != null){
            for (Book tempBook : author.getBooks()) {
                this.books.add(tempBook.getId());
            }
        }
    }
}
