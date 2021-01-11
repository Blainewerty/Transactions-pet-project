package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.DaoFactory;
import ru.milov.transactions.domain.UserDto;
import ru.milov.transactions.dao.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class SQLActions {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void registerNewUser(UserDto userDto){
        getUserDao().insert(userDto);
        System.out.println("User with email: " + userDto.getEmail() + " has registered!");
    }

    public void getInfoAboutUserFromSQL(UserDto userDto) {
        System.out.println(getUserDao().findById(userDto) + "\n");
    }

    public void getAllOperationsOnBill(UserDto userDto){
        List<UserDto> userDtoList = new ArrayList<>();
        getUserDao().findByAll(userDto, userDtoList);
        for (UserDto userDtoOperat : userDtoList) {
            System.out.println(userDtoOperat);
        }
    }

    public void addInfoAboutUsersBillsToSQL(UserDto userDto) throws IOException {
//        userBill.setDate(String.valueOf(java.time.LocalDate.now()));
//        System.out.println("Add score of transaction");
//        userBill.setTransactions(Integer.parseInt(reader.readLine()));
//        System.out.println("Add name of category\n" +
//                "Salary, Funnies, Automobile, Health");
//        userBill.setNameCategory(reader.readLine());
//        getNameCategoryId(userBill);
//        if (userBill.getTransactionsId() == 1) {
//            userBill.setBalance(userBill.getBalance() + userBill.getTransactions());
//        } else {
//            userBill.setBalance(userBill.getBalance() - userBill.getTransactions());
//        }
//        userDao.update(user,userBill);
    }

    public UserDto getNameCategoryId(UserDto userDto) {
//        switch (user.getNameCategory()) {
//            case "Salary":
//                user.setTransactionsId(1);
//                break;
//            case "Funnies":
//                user.setTransactionsId(2);
//                break;
//            case "Health":
//                user.setTransactionsId(3);
//                break;
//            case "Automobile":
//                user.setTransactionsId(4);
//                break;
//        }
        return userDto;
    }
}
