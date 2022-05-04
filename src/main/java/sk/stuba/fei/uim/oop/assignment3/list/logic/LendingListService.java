package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.ListRequest;

import java.util.List;

@Service
public class LendingListService implements ILendingListService {

    @Autowired
    private LendingListRepository listRepository;

    @Autowired
    private BookService bookService;

    @Override
    public List<LendingList> getAll() {
        return this.listRepository.findAll();
    }

    @Override
    public LendingList create()  {
        return this.listRepository.save(new LendingList());
    }

    @Override
    public LendingList getById(long id) throws NotFoundException {
        LendingList list = this.listRepository.findLendingListById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        return list;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.listRepository.delete(this.getById(id));
    }

    @Override
    public LendingList addToList(long listId, ListRequest data) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getUnlended(listId);
        Book book = this.bookService.getById(data.getBookId());
        //var existingListEntry = this.findListEntryWittBook(list.getLendingList(), data.getBookId());
//        if( existingListEntry == null ) {
//            sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry listEntry = listEntryService.create();
//            listEntry.setBook(bookService.getById(data.getBookId()));
//        }
        this.bookService.changeAmount(data.getBookId(), 1);
        this.listRepository.findLendingListById(listId).getBookList().add(book);
        return this.listRepository.save(list);
    }

    @Override
    public void removeFromList(long listId, ListRequest data) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getUnlended(listId);
        Book book = this.bookService.getById(data.getBookId());
        this.bookService.changeAmount(data.getBookId(), -1);
        list.getBookList().remove(book);
    }

    @Override
    public void lendLendingList(long listId) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getUnlended(listId);
        list.setLended(true);
        //this.lendBooks(list.getLendingList());
    }

    private LendingList getUnlended(long id) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getById(id);
        if(list.isLended()) {
            throw new IllegalOperationException();
        }
        return list;
    }

    private sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry findListEntryWittBook(List<sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry> entries, long bookId) {
        for (var entry : entries) {
            if (entry.getBook().getId().equals(bookId)) {
                return entry;
            }
        }
        return null;
    }

    private void lendBooks(List<Book> books) throws IllegalOperationException {
        for(var book : books) {
            if(book.getAmount() < book.getLendCount() + 1) {
                throw new IllegalOperationException();
            }else{
                book.setLendCount(book.getLendCount() + 1);
            }
        }
    }
}
