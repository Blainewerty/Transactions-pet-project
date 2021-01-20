package ru.milov.transactions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.milov.transactions.view.MenuStart;
import ru.milov.transactions.view.MenuButtons;
import ru.milov.transactions.view.MenuConfiguration;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MenuConfiguration.class);
        MenuButtons menu = context.getBean(MenuStart.class);
        menu.start();
    }
}