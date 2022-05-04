package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.List;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.ListRequest;

public interface IListService {

    java.util.List<List> getAll();

    List create();

    List getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    List addToList(long listId, ListRequest data) throws NotFoundException, IllegalOperationException;

    void removeFromList(long listId, ListRequest data) throws NotFoundException, IllegalOperationException;

    void lendLendingList(long listId) throws NotFoundException, IllegalOperationException;
}
