package ru.milov.transactions.view;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ru.milov.transactions.service.SQLActions;
import ru.milov.transactions.service.domain.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class SQLActionsTest extends TestCase {

    UserDto userDto = new UserDto();

    SQLActions sqlActions = new SQLActions();

    @Before
    public void setUp() throws Exception {
        userDto.setEmail("jb@mail.ru");
        userDto.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
    }

    @Test
    public void testGetInfoAboutUserFromSQL_ok() {
        int actual = userDto.getId();
        assertEquals(1, actual);
    }

}