package ru.milov.transactions.Menu;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ru.milov.transactions.dao.UserDao;

import ru.milov.transactions.domain.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class SQLActionsTest extends TestCase {

    UserDto userDto = new UserDto();

    SQLActions sqlActions = new SQLActions();

    @Before
    public void setUp() throws Exception {
        userDto.setNameCategory("Salary");
        userDto.setEmail("jb@mail.ru");
        userDto.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
    }

    @Test
    public void testGetInfoAboutUserFromSQL_ok() {
        int actual = userDto.getId();
        assertEquals(1, actual);
    }

}