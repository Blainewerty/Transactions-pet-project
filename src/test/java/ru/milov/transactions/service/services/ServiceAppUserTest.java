package ru.milov.transactions.service.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.User;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceAppUserTest {

    @Mock
    RepositoryUser repositoryUser;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    Converter<User, ResponseUser> converter;
    @InjectMocks
    ServiceAppUser serviceAppUser;

    @Test
    public void testLoadUserByUsername(){

        when(repositoryUser.findByEmail(anyString())).thenReturn(new User().setEmail("email"));

        assertEquals("email", repositoryUser.findByEmail("email").getEmail());
    }

    @Test
    public void testRegisterUser(){
        User user = new User().setEmail("test");

        when(converter.convert(repositoryUser.save(user))).thenReturn(new ResponseUser().setEmail(user.getEmail()));

        ResponseUser responseUser = converter.convert(repositoryUser.save(user));

        if (responseUser != null) {
            assertEquals("test",responseUser.getEmail());
        }
    }
}