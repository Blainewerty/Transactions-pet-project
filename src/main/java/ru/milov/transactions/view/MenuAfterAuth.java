package ru.milov.transactions.view;

import ru.milov.transactions.service.SQLActions;
import ru.milov.transactions.service.domain.OperationWithBill;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuAfterAuth implements MenuButtons {

    String command;
    SQLActions sqlActions = new SQLActions();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void startMenuAfterAuth(UserDto userDto) {
        do {
            System.out.println("What we do next?\n" +
                    "1: Create Bill\n" +
                    "2: Add Info\n" +
                    "3: Get Current Info\n" +
                    "4: Get All operations\n" +
                    "5: Transfer money to another Bill\n" +
                    "6: Choose another Bill\n" +
                    "7: Go back\n");
            try {
                command = reader.readLine();
                if (command.equals("1")) {
                    buttonOne(userDto);
                }
                if (command.equals("2")) {
                    buttonTwo(userDto);
                }
                if (command.equals("3")) {
                    buttonThree(userDto);
                }
                if (command.equals("4")) {
                    buttonFour(userDto);
                }
                if (command.equals("5")) {
                    buttonFive(userDto);
                }
                if (command.equals("6")) {

                }
                if (command.equals("7")) {
                    buttonClose();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!command.equals("7"));
    }

    @Override
    public void buttonOne() {
    }

    @Override
    public void buttonOne(UserDto userDto) {
        try {
            System.out.println(userDto);
            UserBill userBill = new UserBill();
            OperationWithBill operation = new OperationWithBill();
            System.out.println("Add Score of Transaction");
            operation.setLastTransaction(reader.read());
            System.out.println("You want add or subtract?\n" +
                    "1: Add\n" +
                    "2: Subtract\n" +
                    "3: Abort");
            command = reader.readLine();
            if (command.equals("1")) {
                userBill.setBalance(userBill.getBalance() + operation.getLastTransaction());
            }
            if (command.equals("2")) {
                userBill.setBalance(userBill.getBalance() - operation.getLastTransaction());
            }
            if (command.equals("3")) {
                startMenuAfterAuth(userDto);
            }
            sqlActions.addInfoAboutUsersBillsToSQL(userBill, operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonTwo() {

    }

    @Override
    public void buttonTwo(UserDto userDto) {
        sqlActions.getInfoAboutUserFromSQL(userDto);
    }

    @Override
    public void buttonThree() {

    }

    @Override
    public void buttonThree(UserDto userDto) {

    }

    @Override
    public void buttonFour() {

    }

    @Override
    public void buttonFour(UserDto userDto) {

    }

    @Override
    public void buttonFive() {

    }

    @Override
    public void buttonFive(UserDto userDto) {

    }

    @Override
    public void buttonSix() {

    }

    @Override
    public void buttonSix(UserDto userDto) {

    }

    @Override
    public void buttonClose() {
        System.out.println("Go to the previous Menu");
    }

    @Override
    public void buttonReturn() {

    }
}
