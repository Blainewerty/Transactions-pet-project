package ru.milov.transactions.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.milov.transactions.service.services.DigestService;
import ru.milov.transactions.service.services.ServiceSecurity;
import ru.milov.transactions.service.services.ServiceConverter;
import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserDto;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceFactoryTest extends TestCase {

    @InjectMocks
    ServiceSecurity service;

    @Mock UserDao userDao;
    @Mock
    DigestService digestService;
    @Mock ServiceConverter converter;

    @Test
    public void testAuth_NotFoundByEmail() {
        when(userDao.findByEmail("jb@mail.ru")).thenReturn(null);

        UserDto userDto = service.auth("jb@mail.ru", "1234");

        assertNull(userDto);
    }

    @Test
    public void testAuth_FoundByEmailPassNotEquals() {
        ServiceUser serviceUser = new ServiceUser();
        serviceUser.setPassword("some else");

        when(userDao.findByEmail("jb@mail.ru")).thenReturn(serviceUser);

        when(digestService.digest("1234")).thenReturn("other hash");

        UserDto userDto = service.auth("jb@mail.ru", "1234");

        assertNull(userDto);
    }

    @Test
    public void testAuth_ok() {
        UserDto userDto = new UserDto();
        userDto.setId(1);

        ServiceUser serviceUser = new ServiceUser();
        serviceUser.setId(1);
        serviceUser.setPassword("pass");


        when(userDao.findByEmail("jb@mail.ru")).thenReturn(serviceUser);
        when(digestService.digest("1234")).thenReturn("pass");
        when(converter.convertAuth(serviceUser)).thenReturn(userDto);

        UserDto userDtoFromService = service.auth("jb@mail.ru", "1234");

        assertEquals(userDto, userDtoFromService);
    }
}