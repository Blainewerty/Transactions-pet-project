package ru.milov.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.Transaction;
import ru.milov.transactions.service.entity.User;
import java.util.List;

public interface RepositoryTransaction extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndBill (User user, Bill bill);
}
