package ru.milov.transactions.service.services.servicesql;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.milov.transactions.dao.UserBillDao;
import ru.milov.transactions.service.domain.UserBill;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceSQLBillTest extends TestCase {

    @Mock
    UserBillDao userBillDao;

    @Test
    public void testCreateBill_ok() {
        UserBill userBill = new UserBill();

        userBill.setUser_id(1);
        userBill.setName("Any name");

        UserBill userBillInserted = new UserBill();
        userBillInserted.setBill_id(2);

        when(userBillDao.insert(userBill)).thenReturn(userBillInserted);
        assertNotNull(userBillInserted.getBill_id());
    }

    @Test
    public void testFindInfoAboutUsersBillsInDb_ok() {
        UserBill userBill = new UserBill();
        userBill.setUser_id(1);

        List<UserBill> billList = new LinkedList<>();
        List<UserBill> filledBillList = new LinkedList<>();
        filledBillList.add(userBill);

        when(userBillDao.findByAll(userBill,billList)).thenReturn(filledBillList);

        assertNotSame(billList.size(),filledBillList.size());
    }
}