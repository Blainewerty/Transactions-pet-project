package ru.milov.transactions.view;

import ru.milov.transactions.service.ServiceApp;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu implements MenuButtons {

    private String email;
    private String password;
    private String command;

    private  ServiceApp serviceApp = new ServiceApp();
    private MenuAfterAuth menuAfterAuth = new MenuAfterAuth();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void start() {
        do {
            System.out.println("Hello! \nYou want authentication or registration?\n" +
                    "1: Registration\n" +
                    "2: Authentication\n" +
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
    public void start(UserDto userDto) {

    }

    @Override
    public void buttonOne() {
        try {
            System.out.println("For register type email, password");
            email = reader.readLine();
            password = reader.readLine();

            UserDto userDto = serviceApp.checkIfUserInDb(email, password);

            System.out.println("For register type your first name, last name");

            String firstName = reader.readLine();
            String lastName = reader.readLine();

            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);

            System.out.println("User with email: " + userDto.getEmail() + " has registered!");

            menuAfterAuth.start(serviceApp.registerUser(userDto));

        } catch (IOException | TypeExceptions e) {
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
            UserDto userDto = serviceApp.authInApp(email, password);
            menuAfterAuth.start(userDto);
        } catch (IOException | TypeExceptions e) {
            System.out.println("Please try again!");;
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
