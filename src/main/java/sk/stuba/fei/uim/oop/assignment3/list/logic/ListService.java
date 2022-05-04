package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.List;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.ListRequest;

@Service
public class ListService implements IListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private BookService bookService;

    @Override
    public java.util.List<List> getAll() {
        return this.listRepository.findAll();
    }

    @Override
    public List create()  {
        return this.listRepository.save(new List());
    }

    @Override
    public List getById(long id) throws NotFoundException {
        List list = this.listRepository.findLendingListById(id);
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
    public List addToList(long listId, ListRequest data) throws NotFoundException, IllegalOperationException {
        List list = this.getUnlended(listId);
        Book book = this.bookService.getById(data.getBookId());
        // this.bookService.changeAmount(data.getBookId(), 1);
        this.listRepository.findLendingListById(listId).getLendingList().add(book);
        return this.listRepository.save(list);
    }

    @Override
    public void removeFromList(long listId, ListRequest data) throws NotFoundException, IllegalOperationException {
        List list = this.getUnlended(listId);
        Book book = this.bookService.getById(data.getBookId());
        // this.bookService.changeAmount(data.getBookId(), -1);
        list.getLendingList().remove(book);
    }

    @Override
    public void lendLendingList(long listId) throws NotFoundException, IllegalOperationException {
        List list = this.getUnlended(listId);
        list.setLended(true);
        this.lendBooks(list.getLendingList());
    }

    private List getUnlended(long id) throws NotFoundException, IllegalOperationException {
        List list = this.getById(id);
        if(list.isLended()) {
            throw new IllegalOperationException();
        }
        return list;
    }

//    private sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry findListEntryWittBook(List<sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry> entries, long bookId) {
//        for (var entry : entries) {
//            if (entry.getBook().getId().equals(bookId)) {
//                return entry;
//            }
//        }
//        return null;
//    }

    private void lendBooks(java.util.List<Book> books) throws IllegalOperationException {
        for(var book : books) {
            if(book.getAmount() < book.getLendCount() + 1) {
                throw new IllegalOperationException();
            }else{
                book.setLendCount(book.getLendCount() + 1);
            }
        }
    }
}
