package ru.milov.transactions.repository;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milov.transactions.service.entity.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class RepositoryUserTest extends TestCase {

    @Autowired
    RepositoryUser repositoryUser;

    @BeforeEach
    public void setUp(){

        User user = new User().setEmail("test").setPassword("1234").setFirstName("test").setLastName("test");

        repositoryUser.save(user);

    }

    @Test
    void findByEmail() {

        assertEquals("test",repositoryUser.findByEmail("test").getEmail());

    }

}