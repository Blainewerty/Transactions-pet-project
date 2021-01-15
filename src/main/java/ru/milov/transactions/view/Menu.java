package ru.milov.transactions.view;

import ru.milov.transactions.service.services.serviceapp.ServiceAppUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.services.ServiceFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu implements MenuButtons <UserDto> {

    private String email;
    private String password;
    private String command;

    private final ServiceAppUser serviceAppUser = ServiceFactory.getServiceAppUser();
    private final MenuUser menuUser = new MenuUser();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

            if (email.equals("q") | password.equals("q")){
                start();
            }

            UserDto userDto = serviceAppUser.checkIfUserInDb(email, password);

            System.out.println("For register type your first name, last name");

            String firstName = reader.readLine();
            String lastName = reader.readLine();

            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);

            System.out.println("User with email: " + userDto.getEmail() + " has registered!");

            menuUser.start(serviceAppUser.registerUser(userDto));

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

            UserDto userDto = serviceAppUser.authInApp(email, password);

            serviceAppUser.updateUserInfo(userDto);

            menuUser.start(userDto);
        } catch (IOException | TypeExceptions e) {
            System.out.println("Please try again!");
        }
    }

    @Override
    public void buttonTwo(UserDto userDto) {

    }

    @Override
    public void buttonThree(UserDto userDto) {

    }

    @Override
    public void buttonFour(UserDto userDto) {

    }

    @Override
    public void buttonFive(UserDto userDto) {

    }

    @Override
    public void buttonClose() {
        System.out.println("GoodBye!");
    }

}