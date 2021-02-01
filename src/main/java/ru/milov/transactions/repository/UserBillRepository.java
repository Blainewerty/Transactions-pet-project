package ru.milov.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.UserBill;

public interface UserBillRepository extends JpaRepository<UserBill, Long> {

    UserBill findByName(String userBillName);
}

