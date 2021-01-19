package ru.milov.transactions.service.services.serviceapp;

import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import static ru.milov.transactions.dao.DaoFactory.getUserBillDao;
import java.util.LinkedList;
import java.util.List;

public class ServiceAppBill {

    public List getInfoAboutAllBillsOfUser(UserDto userDto) throws TypeExceptions {
        if (userDto != null) {
            UserBill userBill = new UserBill();
            userBill.setUser_id(userDto.getId());
            List<UserBill> billList = new LinkedList<>();
            return getUserBillDao().findByAll(userBill, billList);
        } else throw new TypeExceptions("Problem with User Info!");
    }

    public void createUserBill(UserDto userDto, String nameOfBill, int balance) {
        UserBill userBill = new UserBill();

        userBill.setUser_id(userDto.getId());
        userBill.setBalance(balance);
        userBill.setName(nameOfBill);
        getUserBillDao().insert(userBill);
    }

    public int countOfBillsMustBeBelowFive(UserDto userDto) throws TypeExceptions {
        return getInfoAboutAllBillsOfUser(userDto).size();
    }

    public void updateUserBill(UserBill userBill){
        getUserBillDao().update(userBill);
    }
}
