package sk.stuba.fei.uim.oop.assignment3.list.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListEntryRepository extends JpaRepository<ListEntry, Long> {

    ListEntry findListEntryById(Long id);
}
