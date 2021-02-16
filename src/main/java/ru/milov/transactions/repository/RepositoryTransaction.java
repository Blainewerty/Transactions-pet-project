package ru.milov.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.Transaction;

public interface RepositoryTransaction extends JpaRepository<Transaction, Long> {

}
