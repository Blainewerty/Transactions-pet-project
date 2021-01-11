package ru.milov.transactions.Menu;

import ru.milov.transactions.dao.ServiceFactory;
import ru.milov.transactions.service.DigestService;
import ru.milov.transactions.service.SecurityService;
import ru.milov.transactions.domain.UserDto;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class MenuActions {


    UserDto userDto;
    String command;
    String email;
    String password;

    SQLActions sqlActions = new SQLActions();
    DigestService digest = new DigestService();
    SecurityService service = ServiceFactory.getSecurityService();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void view(String string) {
        System.out.println("For " + string + " type email, password");
    }


    public void registration() throws IOException {
        userDto = new UserDto();
        view("registration");
        email = reader.readLine();
        password = digest.digest(reader.readLine());
        userDto.setEmail(email);
        userDto.setPassword(password);
        sqlActions.registerNewUser(userDto);
    }

    public void authentication() throws IOException {
        view("authentication");
        email = reader.readLine();
        password = reader.readLine();
        userDto = service.auth(email,password);
        System.out.println("Hello " + userDto.getEmail() + "\nPlease choose your bill:\n" +
                "Person,Work,Saving or show all ");
        String nameOfBill = reader.readLine();
        if (nameOfBill.equals("all")){
            sqlActions.showAllUsersBills(userDto);
        }
        userDto.setNameOfBill(nameOfBill);
        workingWithSQL(userDto);
    }


    public void workingWithSQL(UserDto userDto) throws IOException {
        do {
            getUserDao().findById(userDto);
            System.out.println("What we do next?\n" +
                    "1: Add Info\n" +
                    "2: Get Current Info\n" +
                    "3: Get All operations\n" +
                    "4: Choose another Bill\n" +
                    "5: Go back\n");
            command = reader.readLine();
            switch (command) {
                case "1":
                    sqlActions.addInfoAboutUsersBillsToSQL(userDto);
                    break;
                case "2":
                    sqlActions.getInfoAboutUserFromSQL(userDto);
                    break;
                case "3":
                    sqlActions.getAllOperationsOnBill(userDto);
                    break;
                case "4":
                    System.out.println("Please choose your bill:\n" +
                            "Person,Work,Saving ");
                    String nameOfBill = reader.readLine();
                    userDto.setNameOfBill(nameOfBill);
                    break;
                case "5":
                    break;
            }
        } while (!command.equals("5"));
    }
}


