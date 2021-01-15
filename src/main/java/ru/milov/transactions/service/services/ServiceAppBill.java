package ru.milov.transactions.service.services;

import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.sqlactions.SQLActionsBill;
import java.util.LinkedList;
import java.util.List;

public class ServiceAppBill {

    SQLActionsBill sqlActionsBill = new SQLActionsBill();

    public List<?> showInfoAboutUserBills(UserDto userDto) throws TypeExceptions {
        if (userDto != null) {
            return getInfoAboutAllBillsOfUser(userDto);
        } else throw new TypeExceptions("Problem with User Info!");
    }

    public List getInfoAboutAllBillsOfUser(UserDto userDto) throws TypeExceptions {
        if (userDto != null) {
            UserBill userBill = new UserBill();
            userBill.setUser_id(userDto.getId());
            List<UserBill> billList = new LinkedList<>();
            return sqlActionsBill.findInfoAboutUsersBillsInDb(userBill, billList);
        } else throw new TypeExceptions("Problem with User Info!");
    }

    public void createUserBill(UserDto userDto, String nameOfBill, int balance) {
        UserBill userBill = new UserBill();

        userBill.setUser_id(userDto.getId());
        userBill.setBalance(balance);
        userBill.setName(nameOfBill);

        sqlActionsBill.createBill(userBill);
    }

    public void updateUserBill(UserBill userBill){
        sqlActionsBill.updateBillInfo(userBill);
    }

}
