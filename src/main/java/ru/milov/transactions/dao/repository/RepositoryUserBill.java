package ru.milov.transactions.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.dao.response.ResponseUserDto;
import ru.milov.transactions.service.entity.UserBill;

import java.util.List;

public interface RepositoryUserBill extends JpaRepository<UserBill, Long> {

    UserBill findByUserDtoAndName(String userBillName, ResponseUserDto responseUserDto);

    List<UserBill> findAllByUserDto(ResponseUserDto responseUserDto);


}

