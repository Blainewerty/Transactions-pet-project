package ru.milov.transactions.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    MenuFunc menuFunc = new MenuActions();

    String command;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start() throws IOException {

        do {
            System.out.println("Hello! \nYou want authentication or registration?\n" +
                    "1: Authentication\n" +
                    "2: Registration\n" +
                    "3: Exit");
            command = reader.readLine();
            switch (command) {
                case "1":
                    menuFunc.authentication();
                    break;
                case "2":
                    menuFunc.registration();
                    break;
                case "3":
                    break;
            }
        } while (!command.equals("3"));
    }
}
