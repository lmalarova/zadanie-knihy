package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRequest {
    private long bookId;

    public ListRequest(sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry listEntry) {
        this.bookId = listEntry.getBook().getId();
    }
}
