package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.list.data.List;

import java.util.ArrayList;

@Getter
public class ListResponse {
    private final long id;
    private final java.util.List<BookResponse> lendingList;
    private final boolean lended;

    public ListResponse(List list) {
        this.id = list.getId();
        this.lendingList = this.getBookResponse(list.getLendingList());
        this.lended = list.isLended();
    }

    private java.util.List<BookResponse> getBookResponse(java.util.List<Book> lendingList) {
        java.util.List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book: lendingList) {
            bookResponses.add(new BookResponse(book));
        }
        return bookResponses;
    }
}
