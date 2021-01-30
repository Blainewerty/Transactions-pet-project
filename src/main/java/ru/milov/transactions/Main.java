package ru.milov.transactions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.milov.transactions.dao.JpaConfiguration;
import ru.milov.transactions.service.entity.UserDto;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(MenuConfiguration.class);
//        MenuButtons menu = context.getBean(MenuStart.class);
//        menu.start();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);

        EntityManager em = context.getBean(EntityManager.class);

        UserDto userDto = em.find(UserDto.class,1);

        System.out.println(userDto);
    }
}