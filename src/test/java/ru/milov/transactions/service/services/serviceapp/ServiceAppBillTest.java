package ru.milov.transactions.service.services.serviceapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.milov.transactions.dao.UserBillDao;
import ru.milov.transactions.service.domain.UserBill;
import java.util.LinkedList;
import java.util.List;

public class ServiceAppBillTest {

    UserBillDao userBillDao;

    public ServiceAppBillTest(UserBillDao userBillDao) {
        this.userBillDao = userBillDao;
    }

    @Before
    public void setUp() {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        System.setProperty("jdbcUser", "user");
        System.setProperty("jdbcPassword", "");
    }

    @Test
    public void testGetInfoAboutAllBillsOfUser() {
        testCreateUserBill();

        UserBill userBill = new UserBill();

        List<UserBill> billList = new LinkedList<>();
        userBillDao.findByAll(userBill, billList);
    }

    @Test
    public void testCreateUserBill() {

        UserBill userBill = new UserBill();

        userBill.setUser_id(1);
        userBill.setName("Any name");
        userBill.setBalance(200);

        userBillDao.insert(userBill);

        Assert.assertEquals(userBill.getBalance(), userBillDao.findById(userBill).getBalance());
    }

    @Test
    public void testCountOfBillsMustBeBelowFive() {

    }

    @Test
    public void testUpdateUserBill() {
        testCreateUserBill();

        UserBill userBill = new UserBill();
        userBill.setUser_id(1);
        UserBill userBillFromDb = userBillDao.findById(userBill);

        userBillFromDb.setBalance(600);
        userBillDao.update(userBillFromDb);

        Assert.assertEquals(userBillFromDb.getBalance(), userBillDao.findById(userBill).getBalance());
    }
}