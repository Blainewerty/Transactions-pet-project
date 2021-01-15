package ru.milov.transactions.view;

import ru.milov.transactions.service.services.ServiceAppTransaction;
import ru.milov.transactions.service.domain.UserBill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuOfBill implements MenuButtons<UserBill> {

    private String command;
    private final ServiceAppTransaction serviceAppTransaction = new ServiceAppTransaction();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void start() {

    }

    @Override
    public void start(UserBill userBill) {
        do {
            try {
                System.out.println(userBill + "\n" +
                        "What would you do next?" + "\n" +
                        "1: Update bill" + "\n" +
                        "2: Transfer to another bill" + "\n" +
                        "3: Delete bill");

                command = reader.readLine();

                if (command.equals("1")) {
                    buttonOne(userBill);
                }
                if (command.equals("2")) {
                    buttonTwo(userBill);
                }
                if (command.equals("3")) {
                    buttonThree(userBill);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!command.equals("q"));
    }

    @Override
    public void buttonOne() {

    }

    @Override
    public void buttonOne(UserBill userBill) {

        try {
            System.out.println("You want add or submit ?" + "\n" +
                    "1: Add" + "\n" +
                    "2: Submit");

            command = reader.readLine();

            if (command.equals("q")){
                start(userBill);
            }

            System.out.println("Value of operation ?");

            int valueOfOperation = Integer.parseInt(reader.readLine());

            if (valueOfOperation>userBill.getBalance()){
                System.out.println("Transaction is more than value on bill! \n"+
                        "Try again!");
                buttonOne(userBill);
            }

            System.out.println("How do we name a transaction ?");

            String nameOfTransaction = reader.readLine();

            serviceAppTransaction.startingOperationWithBill(userBill,nameOfTransaction,valueOfOperation,command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonTwo() {

    }

    @Override
    public void buttonTwo(UserBill userDto) {

    }

    @Override
    public void buttonThree(UserBill userDto) {

    }

    @Override
    public void buttonFour(UserBill userDto) {

    }

    @Override
    public void buttonFive(UserBill userDto) {

    }

    @Override
    public void buttonClose() {

    }

}
