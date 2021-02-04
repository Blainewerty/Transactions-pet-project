package ru.milov.transactions.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.entity.Bill;

import java.util.List;

public interface RepositoryBill extends JpaRepository<Bill, Long> {

    Bill findByUserAndName(String userBillName, ResponseUser responseUser);

    Bill findByName(String nameOfBill);

    List<Bill> findAllByUser(ResponseUser responseUser);


}

