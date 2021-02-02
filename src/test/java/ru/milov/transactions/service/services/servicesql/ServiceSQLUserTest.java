//package ru.milov.transactions.service.services.servicesql;
//
//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import ru.milov.transactions.service.entity.UserDto;
//
//import java.math.BigDecimal;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ServiceSQLUserTest extends TestCase {
//
//    @Mock
//    UserDao userDao;
//
//    @Test
//    public void testInsertUserToDb_ok() {
//        UserDto userDto = new UserDto();
//        userDto.setEmail("jb@mail.ru");
//        userDto.setPassword("1234");
//        userDto.setFirstName("Alex");
//
//        UserDto userDtoInserted = new UserDto();
//        userDtoInserted.setUser_id(1L);
//
//        when(userDao.insert(userDto)).thenReturn(userDtoInserted);
//        assertNotNull(userDtoInserted.getUser_id());
//    }
//
//    @Test
//    public void testInsertUserToDb_notOk() {
//        UserDto userDto = new UserDto();
//        userDto.setEmail("jb@mail.ru");
//        userDto.setPassword("1234");
//        userDto.setFirstName("Alex");
//
//        UserDto userDtoInserted = new UserDto();
//        userDtoInserted.setUser_id(null);
//
//        when(userDao.insert(userDto)).thenReturn(userDtoInserted);
//        assertNull(userDtoInserted.getUser_id());
//    }
//
//    @Test
//    public void testFillingUserInfo_Ok() {
//        UserDto userDto = new UserDto();
//        userDto.setUser_id(1L);
//
//        UserDto userDtoWithInfo = new UserDto();
//
//        userDtoWithInfo.setFirstName("Alex");
//
//        when(userDao.findById(userDto)).thenReturn(userDtoWithInfo);
//
//        assertEquals("Alex",userDtoWithInfo.getFirstName());
//    }
//
//    @Test
//    public void testUpdateUserInfo_Ok() {
//        UserDto userDto = new UserDto();
//        userDto.setUser_id(1L);
//
//        UserDto userDtoWithInfo = new UserDto();
//
//        userDtoWithInfo.setTotalBalance(BigDecimal.valueOf(15));
//
//        when(userDao.findById(userDto)).thenReturn(userDtoWithInfo);
//
//        assertEquals(15,userDtoWithInfo.getTotalBalance());
//    }
//}