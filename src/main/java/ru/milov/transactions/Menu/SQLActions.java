package ru.milov.transactions.Menu;

import ru.milov.transactions.domain.UserDto;
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

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void registerNewUser(UserDto userDto) {
        getUserDao().insert(userDto);
        System.out.println("User with email: " + userDto.getEmail() + " has registered!");
    }

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

    public void addInfoAboutUsersBillsToSQL(UserDto userDto) throws IOException {
        Date date = new Date();
        Timestamp ts1 = new Timestamp(date.getTime());

        userDto.setDate(sdf.format(ts1));
        System.out.println("Add score of transaction");
        userDto.setLastTransaction(Integer.parseInt(reader.readLine()));
        System.out.println("Add name of category\n" +
                "Salary, Funnies, Automobile, Health");
        userDto.setNameCategory(reader.readLine());
        System.out.println("You want add, or subtract money?\n" +
                "1 for add\n" +
                "2 for subtract");
        switch (Integer.parseInt(reader.readLine())) {
            case 1:
                userDto.setBalance(userDto.getBalance() + userDto.getLastTransaction());
                getUserDao().update(userDto);
                break;
            case 2:
                userDto.setBalance(userDto.getBalance() - userDto.getLastTransaction());
                getUserDao().update(userDto);
                break;
        }
    }

    public void showAllUsersBills(UserDto userDto) {
        userDto.setNameOfBill("Person");
        System.out.println(getUserDao().findById(userDto));
        userDto.setNameOfBill("Work");
        System.out.println(getUserDao().findById(userDto));
        userDto.setNameOfBill("Saving");
        System.out.println(getUserDao().findById(userDto));
    }

    public UserDto transferFromBillToBill(UserDto userDto) throws IOException {
        getUserDao().findById(userDto);
        String tempNameOfBill = userDto.getNameOfBill();
        int tempBalance = userDto.getBalance();
        System.out.println("Where transfer?\n" +
                "Person, Work, Saving");
        String nameOfTransferBill = reader.readLine();
        userDto.setNameOfBill(nameOfTransferBill);
        getUserDao().findById(userDto);
        System.out.println("Add score of transaction");
        userDto.setLastTransaction(Integer.parseInt(reader.readLine()));
        userDto.setBalance(userDto.getLastTransaction() + userDto.getBalance());
        getUserDao().update(userDto);
        userDto.setNameOfBill(tempNameOfBill);
        userDto.setBalance(tempBalance - userDto.getLastTransaction());
        getUserDao().update(userDto);
        return userDto;
    }
}


