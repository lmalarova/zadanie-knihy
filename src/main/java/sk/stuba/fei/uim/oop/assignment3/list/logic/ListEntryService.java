package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListEntry;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListEntryRepository;

@Service
public class ListEntryService implements IListEntryService {

    @Autowired
    ListEntryRepository repository;

    @Override
    public ListEntry create() {
        return this.repository.save(new ListEntry());
    }

    @Override
    public ListEntry getById(long id) throws NotFoundException {
        ListEntry listEntry = this.repository.findListEntryById(id);
        if (listEntry == null)
            throw new NotFoundException();
        return listEntry;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public ListEntry save(ListEntry listEntry) {
        return this.repository.save(listEntry);
    }
}
