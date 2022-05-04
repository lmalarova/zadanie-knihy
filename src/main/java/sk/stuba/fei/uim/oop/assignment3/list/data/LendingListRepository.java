package sk.stuba.fei.uim.oop.assignment3.list.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingListRepository extends JpaRepository<LendingList, Long> {
    LendingList findLendingListById(Long id);
}
