package ru.milov.transactions.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.Transaction;

public interface RepositoryTransaction extends JpaRepository<Transaction, Long> {

}
