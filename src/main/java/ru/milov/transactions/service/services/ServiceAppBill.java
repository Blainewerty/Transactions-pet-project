package ru.milov.transactions.service.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.Dao;
import ru.milov.transactions.view.TypeExceptions;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAppBill {

    private final Dao <UserBill, Integer> userBillDao;

    public List getInfoAboutAllBillsOfUser(UserDto userDto) throws TypeExceptions {
        if (userDto != null) {
            UserBill userBill = new UserBill();
            userBill.setUser_id(userDto.getId());
            List<UserBill> billList = new LinkedList<>();
            return userBillDao.findByAll(userBill, billList);
        } else throw new TypeExceptions("Problem with User Info!");
    }

    public void createUserBill(UserDto userDto, String nameOfBill, int balance) {
        UserBill userBill = new UserBill();

        userBill.setUser_id(userDto.getId());
        userBill.setBalance(balance);
        userBill.setName(nameOfBill);
        userBillDao.insert(userBill);
    }

    public int countOfBillsMustBeBelowFive(UserDto userDto) throws TypeExceptions {
        return getInfoAboutAllBillsOfUser(userDto).size();
    }

    public void updateUserBill(UserBill userBill) {
        userBillDao.update(userBill);
    }

    public boolean deleteUserBill(UserBill userBill) {
        return userBillDao.delete(userBill.getBill_id());
    }
}
