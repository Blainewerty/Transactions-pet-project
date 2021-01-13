package ru.milov.transactions.view;

import ru.milov.transactions.service.ServiceFactory;
import ru.milov.transactions.service.domain.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class Menu implements MenuButtons {

    String email;
    String password;
    String command;
    UserDto userDto;

    MenuAfterAuth menuAfterAuth = new MenuAfterAuth();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void startApp() {
        do {
            System.out.println("Hello! \nYou want authentication or registration?\n" +
                    "1: Authentication\n" +
                    "2: Registration\n" +
                    "3: Exit");

            try {
                command = reader.readLine();
                if (command.equals("1")) {
                    buttonOne();
                }
                if (command.equals("2")) {
                    buttonTwo();
                }
                if (command.equals("3")) {
                    buttonClose();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!command.equals("3"));
    }


    @Override
    public void buttonOne() {
        try {
            UserDto userDto = new UserDto();
            System.out.println("For register type email, password");
            email = reader.readLine();
            password = ServiceFactory.getDigestService().digest(reader.readLine());
            userDto.setEmail(email);
            userDto.setPassword(password);

            System.out.println("For register type your first name, last name and balance");
            String firstName = reader.readLine();
            String lastName = reader.readLine();
            int balance = reader.read();

            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setTotalBalance(balance);

            getUserDao().insert(userDto);
            System.out.println("User with email: " + userDto.getEmail() + " has registered!");

            menuAfterAuth.startMenuAfterAuth(userDto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonOne(UserDto userDto) {

    }

    @Override
    public void buttonTwo() {
        try {
            System.out.println("For authentication type email, password");
            email = reader.readLine();
            password = reader.readLine();
            userDto = ServiceFactory.getServiceSecurity().auth(email, password);
            if (userDto != null) {
                menuAfterAuth.startMenuAfterAuth(userDto);
            } else {
                System.out.println("Please try again!");
                startApp();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buttonTwo(UserDto userDto) {

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
        System.out.println("GoodBye!");
    }

    @Override
    public void buttonReturn() {

    }
}
