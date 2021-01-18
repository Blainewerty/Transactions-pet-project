package ru.milov.transactions.service.services.servicesql;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.domain.UserDto;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceSQLUserTest extends TestCase {

    @Mock
    UserDao userDao;

    @Test
    public void testInsertUserToDb_ok() {
        UserDto userDto = new UserDto();
        userDto.setEmail("jb@mail.ru");
        userDto.setPassword("1234");
        userDto.setFirstName("Alex");

        UserDto userDtoInserted = new UserDto();
        userDtoInserted.setId(1);

        when(userDao.insert(userDto)).thenReturn(userDtoInserted);
        assertNotNull(userDtoInserted.getId());
    }

    @Test
    public void testInsertUserToDb_notOk() {
        UserDto userDto = new UserDto();
        userDto.setEmail("jb@mail.ru");
        userDto.setPassword("1234");
        userDto.setFirstName("Alex");

        UserDto userDtoInserted = new UserDto();
        userDtoInserted.setId(null);

        when(userDao.insert(userDto)).thenReturn(userDtoInserted);
        assertNull(userDtoInserted.getId());
    }

    @Test
    public void testFillingUserInfo_Ok() {
        UserDto userDto = new UserDto();
        userDto.setId(1);

        UserDto userDtoWithInfo = new UserDto();

        userDtoWithInfo.setFirstName("Alex");

        when(userDao.findById(userDto)).thenReturn(userDtoWithInfo);

        assertEquals("Alex",userDtoWithInfo.getFirstName());
    }

    @Test
    public void testUpdateUserInfo_Ok() {
        UserDto userDto = new UserDto();
        userDto.setId(1);

        UserDto userDtoWithInfo = new UserDto();

        userDtoWithInfo.setTotalBalance(15);

        when(userDao.findById(userDto)).thenReturn(userDtoWithInfo);

        assertEquals(15,userDtoWithInfo.getTotalBalance());
    }
}