package ru.milov.transactions.service;

import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.milov.transactions.dao.DaoFactory.*;

public class SQLActions {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public UserDto fillingUserInfo(UserDto userDto){
       return getUserDao().findById(userDto);
    }

    public UserDto insertUserToDb(UserDto userDto){
        return getUserDao().insert(userDto);
    }

    public void findInfoAboutUsersBillsInDb(UserBill userBill) {
        getUserBillDao().findById(userBill);

    }

    public void getAllOperationsOnBill(UserDto userDto) {
        List<UserDto> userDtoList = new ArrayList<>();
        getUserDao().findByAll(userDto, userDtoList);
        for (UserDto userDtoOperat : userDtoList) {
            System.out.println(userDtoOperat);
        }
    }

    public void addOperationOfBillToSQL (Transaction operation){
        Date date = new Date();
        Timestamp ts1 = new Timestamp(date.getTime());
        operation.setDate(sdf.format(ts1));
    }

    public UserBill createBill(UserBill userBill) {
        return getUserBillDao().insert(userBill);
    }

    public void getInfoAboutUserWithBillsFromSql (UserBill userBill){

    }
}


