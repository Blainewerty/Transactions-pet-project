package ru.milov.transactions.view;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ru.milov.transactions.service.sqlactions.SQLActionsBill;
import ru.milov.transactions.service.domain.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class SQLActionsBillTest extends TestCase {

    UserDto userDto = new UserDto();

    SQLActionsBill sqlActionsBill = new SQLActionsBill();

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