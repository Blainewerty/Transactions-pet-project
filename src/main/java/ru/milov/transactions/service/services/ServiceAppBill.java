package ru.milov.transactions.service.services;

import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.UserBillDao;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import java.util.LinkedList;
import java.util.List;

@Service
public class ServiceAppBill {

    private final UserBillDao userBillDao;

    public ServiceAppBill(UserBillDao userBillDao) {
        this.userBillDao = userBillDao;
    }

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
