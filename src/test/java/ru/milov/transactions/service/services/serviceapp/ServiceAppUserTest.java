package ru.milov.transactions.service.services.serviceapp;

import junit.framework.TestCase;
import org.junit.Before;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.services.ServiceSecurity;

public class ServiceAppUserTest extends TestCase {

    UserDao userDao;
    ServiceSecurity serviceSecurity;

    public ServiceAppUserTest() {
    }

    public ServiceAppUserTest(String name) {
        super(name);
    }

    @Before
    public void setUp() {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        System.setProperty("jdbcUser", "user");
        System.setProperty("jdbcPassword", "");
    }

    public void testRegisterUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setTotalBalance(0);
        userDto.setEmail("SomeOne");

        userDao.insert(userDto);

        UserDto userDto1 = userDao.findById(userDto);

        assertEquals(userDto,userDto1);
    }

    public void testCheckIfUserInDb() {
        String email = "SomeThing";
        String password ="Someone";
        UserDto userDto = serviceSecurity.checkIfUserInDb(email, password);

        assertNotNull(userDto);
    }

    public void testUpdateUserInfo() {
    }



    public void testAuthInApp() {
    }
}