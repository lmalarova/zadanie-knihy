package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        return this.authorRepository.save(new Author(request));
    }

    @Override
    public Author getById(long id) throws NotFoundException {
        Author author = this.authorRepository.findAuthorById(id);
        if (author == null) {
            throw new NotFoundException();
        }
        return author;
    }

    @Override
    public Author update(long id, AuthorRequest request) throws NotFoundException {
        Author author = this.getById(id);
        if (request.getName() != null) {
            author.setName(request.getName());
        }
        if (request.getSurname() != null) {
            author.setSurname(request.getSurname());
        }
        return this.authorRepository.save(author);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        List<Book> books = this.getById(id).getBooks();
        if(books != null){
            this.bookRepository.deleteAll(books);
        }
        this.authorRepository.delete(this.getById(id));
    }
}
