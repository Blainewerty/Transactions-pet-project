package ru.milov.transactions.view;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ru.milov.transactions.service.services.servicesql.ServiceSQLBill;
import ru.milov.transactions.service.domain.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class ServiceSQLBillTest extends TestCase {

    UserDto userDto = new UserDto();

    ServiceSQLBill serviceSqlBill = new ServiceSQLBill();

    @Before
    public void setUp(){
        userDto.setEmail("jb@mail.ru");
        userDto.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
    }

    @Test
    public void testGetInfoAboutUserFromSQL_ok() {
        int actual = userDto.getId();
        assertEquals(1, actual);
    }

}