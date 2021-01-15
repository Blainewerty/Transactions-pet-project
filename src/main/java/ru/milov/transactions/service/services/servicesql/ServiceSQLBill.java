package ru.milov.transactions.service.services.servicesql;

import ru.milov.transactions.service.domain.UserBill;
import java.util.*;
import static ru.milov.transactions.dao.DaoFactory.getUserBillDao;


public class ServiceSQLBill {

    public UserBill createBill(UserBill userBill) {
        return getUserBillDao().insert(userBill);
    }

    public List findInfoAboutUsersBillsInDb(UserBill userBill, List<UserBill> billList) {
        return getUserBillDao().findByAll(userBill, billList);
    }

    public void updateBillInfo(UserBill userBill){
        getUserBillDao().update(userBill);
    }
}


