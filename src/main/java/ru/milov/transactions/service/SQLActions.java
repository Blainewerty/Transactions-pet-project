package ru.milov.transactions.service;

import ru.milov.transactions.service.domain.OperationWithBill;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class SQLActions {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public void getInfoAboutUserFromSQL(UserDto userDto) {
        System.out.println(getUserDao().findById(userDto) + "\n");
    }

    public void getAllOperationsOnBill(UserDto userDto) {
        List<UserDto> userDtoList = new ArrayList<>();
        getUserDao().findByAll(userDto, userDtoList);
        for (UserDto userDtoOperat : userDtoList) {
            System.out.println(userDtoOperat);
        }
    }

    public void addInfoAboutUsersBillsToSQL(UserBill userBill, OperationWithBill operation) throws IOException {
        Date date = new Date();
        Timestamp ts1 = new Timestamp(date.getTime());
        operation.setDate(sdf.format(ts1));


    }
}


