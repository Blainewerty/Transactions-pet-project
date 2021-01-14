package ru.milov.transactions.view;


import ru.milov.transactions.service.ServiceApp;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuAfterAuth implements MenuButtons {

    private String command;
    private  ServiceApp serviceApp = new ServiceApp();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void start() {
    }

    @Override
    public void start(UserDto userDto) {
        do {
            System.out.println("Hello " + userDto.getFirstName() + " Your Total balance is " + userDto.getTotalBalance() + "\n"+
                    "What we do next?\n" +
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
            System.out.println("How do we name it?");
            String nameOfTransaction = reader.readLine();

            System.out.println("Add Score of Transaction");
            int valueOfTransaction = Integer.parseInt(reader.readLine());


            System.out.println("You want add or subtract?\n" +
                    "1: Add\n" +
                    "2: Subtract\n" +
                    "3: Abort");
            command = reader.readLine();

            serviceApp.startingOperationWithBill(userDto, nameOfTransaction, valueOfTransaction, command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonTwo() {

    }

    @Override
    public void buttonTwo(UserDto userDto) {

    }

    @Override
    public void buttonThree() {

    }

    @Override
    public void buttonThree(UserDto userDto) {
        try {
            serviceApp.getInfoAboutUser(userDto);
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
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
