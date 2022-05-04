package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book create(BookRequest request) {
        Author author = this.authorRepository.findAuthorById(request.getAuthor());
        Book newBook = this.bookRepository.save(new Book(request));
        newBook.setAuthor(author);
        if(author.getBooks() == null){
            author.setBooks(new ArrayList<>());
        }
        author.getBooks().add(newBook);
        return newBook;
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        Book book = this.bookRepository.findBookById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @Override
    public Book update(long id, BookUpdateRequest request) throws NotFoundException {
        Book book = this.getById(id);
        if (request.getName() != null) {
            book.setName(request.getName());
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        if(request.getAuthor() != 0){
            Author author = this.authorRepository.findAuthorById(request.getAuthor());
            book.setAuthor(author);
        }
        if (request.getPages() != 0) {
            book.setPages(request.getPages());
        }
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        Book book = this.bookRepository.findBookById(id);
        if(book != null){
            Author author = book.getAuthor();
            author.getBooks().remove(book);
        }
        this.bookRepository.delete(this.getById(id));
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(long id, int amountToAdd) throws NotFoundException {
        Book book = this.getById(id);
        book.setAmount(book.getAmount() + amountToAdd);
        this.bookRepository.save(book);
        return book.getAmount();
    }

    @Override
    public int getLendCount(long id) throws NotFoundException {
        return this.getById(id).getLendCount();
    }

    @Override
    public void changeAmount(long id, int amount) throws NotFoundException, IllegalOperationException {
        Book book = this.getById(id);
        if(book.getAmount() == 0) {
            throw new IllegalOperationException();
        }
        book.setAmount(book.getAmount() + amount);
    }
}
