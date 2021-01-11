package ru.milov.transactions;

import ru.milov.transactions.Menu.Menu;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.start();

    }
}