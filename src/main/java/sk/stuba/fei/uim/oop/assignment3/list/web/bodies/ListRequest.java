package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRequest {

    private long bookId;

    public ListRequest(long bookId) {
        this.bookId = bookId;
    }
}
