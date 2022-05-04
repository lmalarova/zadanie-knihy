package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListResponse {
    private final long id;
    private final List<Book> bookList;
    private final boolean lended;

    public ListResponse(LendingList lendingList) {
        this.id = lendingList.getId();
        this.bookList = lendingList.getBookList();
        this.lended = lendingList.isLended();
    }
}
