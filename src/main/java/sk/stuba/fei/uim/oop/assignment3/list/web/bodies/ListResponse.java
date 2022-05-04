package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.List;

@Getter
public class ListResponse {
    private final long id;
    private final java.util.List<Book> lendingList;
    private final boolean lended;

    public ListResponse(List list) {
        this.id = list.getId();
        this.lendingList = list.getLendingList();
        this.lended = list.isLended();
    }
}
