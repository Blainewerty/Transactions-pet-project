package ru.milov.transactions.view;

import org.springframework.stereotype.Service;
import ru.milov.transactions.service.services.ServiceAppBill;
import ru.milov.transactions.service.services.ServiceAppTransaction;
import ru.milov.transactions.service.entity.UserBill;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MenuOfBill implements MenuButtons<UserBill> {

    private String command;
    private final ServiceAppBill serviceAppBill;
    private final ServiceAppTransaction serviceAppTransaction;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public MenuOfBill(ServiceAppBill serviceAppBill, ServiceAppTransaction serviceAppTransaction) {
        this.serviceAppBill = serviceAppBill;
        this.serviceAppTransaction = serviceAppTransaction;
    }

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
                        "2: Get bill operations" + "\n" +
                        "3: Delete bill\n" +
                        "q: Return");

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

            if (command.equals("q")) {
                start(userBill);
            }

            System.out.println("Value of operation ?");

            BigDecimal valueOfOperation = BigDecimal.valueOf(Long.parseLong(reader.readLine()));

//            if (valueOfOperation.compareTo(userBill.getBalance())) {
//                System.out.println("Transaction is more than value on bill! \n" +
//                        "Try again!");
//                buttonOne(userBill);
//            }

            System.out.println("How do we name a transaction ?");

            String nameOfTransaction = reader.readLine();

            serviceAppTransaction.startingOperationWithBill(userBill, nameOfTransaction, valueOfOperation, command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonTwo() {

    }

    @Override
    public void buttonTwo(UserBill userBill) {

        List transactionList = serviceAppTransaction.getInfoAboutBillTransactions(userBill);

        System.out.println("You Have: ");
        for (Object transaction : transactionList) {
            System.out.println(transaction);
        }

        if (command.equals("q")) {
            start(userBill);
        }

    }

    @Override
    public void buttonThree(UserBill userBill) {
        try {
            System.out.println("You want to delete " + userBill.getName() + " ?\n" +
                    "1: Yes\n" +
                    "2: No");

            command = reader.readLine();

            if (command.equals("1")) {
                if (serviceAppBill.deleteUserBill(userBill)) {
                    System.out.println("Complete !");
                    command = "q";
                }
            }
            if (command.equals("2")) {
                start(userBill);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonFour(UserBill userBill) {

    }

    @Override
    public void buttonFive(UserBill userBill) {

    }

    @Override
    public void buttonClose() {

    }

}
