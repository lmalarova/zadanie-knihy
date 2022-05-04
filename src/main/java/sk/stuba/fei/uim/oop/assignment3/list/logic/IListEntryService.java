package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry;

public interface IListEntryService {

    ListEntry create();

    ListEntry getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    ListEntry save(ListEntry cartEntry);
}
