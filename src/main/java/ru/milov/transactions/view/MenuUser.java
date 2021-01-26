package ru.milov.transactions.view;

import org.springframework.stereotype.Service;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.services.ServiceAppBill;
import ru.milov.transactions.service.services.ServiceAppTransaction;
import ru.milov.transactions.service.services.ServiceAppUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MenuUser implements MenuButtons<UserDto> {

    private String command;
    List<UserBill> billList;

    private final ServiceAppUser serviceAppUser;
    private final ServiceAppBill serviceAppBill;
    private final ServiceAppTransaction serviceAppTransaction;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public MenuUser(ServiceAppUser serviceAppUser, ServiceAppBill serviceAppBill, ServiceAppTransaction serviceAppTransaction) {
        this.serviceAppUser = serviceAppUser;
        this.serviceAppBill = serviceAppBill;
        this.serviceAppTransaction = serviceAppTransaction;
    }


    @Override
    public void start() {
    }

    @Override
    public void start(UserDto userDto) {

        System.out.println("Hello " + userDto.getFirstName() + " Your Total balance is " + userDto.getTotalBalance());
        do {
            System.out.println("What we do next?\n" +
                    "1: Create Bill\n" +
                    "2: Choose Bill\n" +
                    "3: Get Current Info\n" +
                    "4: Transfer from bill to bill\n" +
                    "5: Delete User\n" +
                    "q: Go back\n");

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
                if (command.equals("q")) {
                    buttonClose();
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
    public void buttonOne(UserDto userDto) {
        try {
            if (serviceAppBill.countOfBillsMustBeBelowFive(userDto) < 5) {
                System.out.println("Type name of bill");
                String nameOfBill = reader.readLine();
                System.out.println("Type balance of bill");
                int balance = Integer.parseInt(reader.readLine());
                System.out.println(nameOfBill + " " + balance + "\n" +
                        "Correct ?");
                command = reader.readLine();
                if (command.equals("y")) {
                    serviceAppBill.createUserBill(userDto, nameOfBill, balance);
                } else buttonOne(userDto);
            } else {
                System.out.println("Too many bills for user\n");
            }
        } catch (IOException | TypeExceptions e) {
            e.printStackTrace();
        }

    }

    @Override
    public void buttonTwo() {

    }

    @Override
    public void buttonTwo(UserDto userDto) {
        MenuButtons menu = new MenuOfBill(serviceAppBill, serviceAppTransaction);
        try {
            billList = serviceAppBill.getInfoAboutAllBillsOfUser(userDto);

            System.out.println("You Have: ");
            for (int i = 0; i < billList.size(); i++) {
                System.out.println(i + ": " + billList.get(i));
            }
            command = reader.readLine();
            if (command.equals("q")) {
                start(userDto);
            }
            menu.start(billList.get(Integer.parseInt(command)));
        } catch (IOException | TypeExceptions e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonThree(UserDto userDto) {
        try {
            if (serviceAppBill.getInfoAboutAllBillsOfUser(userDto).size() == 0) {
                System.out.println("There is no bills yet!");
            } else for (Object bills : serviceAppBill.getInfoAboutAllBillsOfUser(userDto)) {
                System.out.println(bills);
            }
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
    }

    @Override
    public void buttonFour(UserDto userDto) {
        try {
            billList = serviceAppBill.getInfoAboutAllBillsOfUser(userDto);

            System.out.println("You Have: ");
            for (int i = 0; i < billList.size(); i++) {
                System.out.println(i + ": " + billList.get(i));
            }

            System.out.println("Choose from which bill will be transfer");
            int fromBill = Integer.parseInt(reader.readLine());

            System.out.println("Choose to which bill will be transfer");
            int toBill = Integer.parseInt(reader.readLine());

            System.out.println("Type value of transaction");
            BigDecimal valueOfTransaction = BigDecimal.valueOf(Long.parseLong(reader.readLine()));

//            if (valueOfTransaction.compareTo(billList.get(fromBill).getBalance())) {
//                System.out.println("The value is larger than bill balance");
//                start(userDto);
//            }

            serviceAppTransaction.transferFromBillToBill(billList, fromBill, toBill, valueOfTransaction);

            if (command.equals("q")) {
                start(userDto);
            }

        } catch (IOException | TypeExceptions e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonFive(UserDto userDto) {
        try {
            System.out.println("You want to delete " + userDto.getEmail() + " ?\n" +
                    "1: Yes\n" +
                    "2: No");

            command = reader.readLine();

            if (command.equals("1")) {
                if (serviceAppUser.deleteUserFromDb(userDto)) {
                    System.out.println("Complete !");
                    command = "q";
                }
            }
            if (command.equals("2")) {
                start(userDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonClose() {
        System.out.println("Go to the previous Menu");
    }

}
