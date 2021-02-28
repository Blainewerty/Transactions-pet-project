package ru.milov.transactions.service.services

import org.springframework.core.convert.converter.Converter
import org.springframework.security.crypto.password.PasswordEncoder
import ru.milov.transactions.repository.RepositoryUser
import ru.milov.transactions.response.ResponseUser
import ru.milov.transactions.service.entity.User
import spock.lang.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import static org.mockito.Mockito.*

class ServiceAppUserTestSpock extends Specification {
    @Mock
    RepositoryUser repositoryUser
    @Mock
    PasswordEncoder passwordEncoder
    @Mock
    Converter<User, ResponseUser> converter
    @InjectMocks
    ServiceAppUser serviceAppUser

    def setup() {
        MockitoAnnotations.initMocks(this)
    }

    def "test load User By Username"() {
        given:
        when(repositoryUser.findByEmail(anyString())).thenReturn(new User())

        when:
        User result = serviceAppUser.loadUserByUsername("email") as User

        then:
        result == new User()
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme