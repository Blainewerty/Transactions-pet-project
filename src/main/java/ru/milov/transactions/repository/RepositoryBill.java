package ru.milov.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;

import java.util.List;

public interface RepositoryBill extends JpaRepository<Bill, Long> {

    Bill findByUser_idAndName(Long id, String nameOfBill);

    List<Bill> findAllByUser_id(Long id);

    void deleteByUserAndName(User user, String nameOfBill);

    Bill findByUserAndName(User user, String name);
}

