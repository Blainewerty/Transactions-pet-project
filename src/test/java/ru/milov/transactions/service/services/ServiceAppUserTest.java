package ru.milov.transactions.service.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.milov.transactions.converter.ConverterUserToUserResponse;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.Role;
import ru.milov.transactions.service.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceAppUserTest {

    @Mock
    private RepositoryUser mockRepositoryUser;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private ConverterUserToUserResponse mockConverter;

    private ServiceAppUser serviceAppUserUnderTest;

    @Before
    public void setUp() {
 //       serviceAppUserUnderTest = new ServiceAppUser(mockRepositoryUser, mockPasswordEncoder, mockConverter);
    }

    @Test
    public void testLoadUserByUsername() {
        // Setup

        // Configure RepositoryUser.findByEmail(...).
        final User user = new User();
        user.setId(0L);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setActive(false);
        user.setGoogleName("googleName");
        user.setGoogleUsername("googleUsername");
        final Bill bill = new Bill();
        bill.setId(0L);
        bill.setUser(new User());
        bill.setName("name");
        bill.setBalance(new BigDecimal("0.00"));
        user.setBill(List.of(bill));
        user.setRoles(Set.of(Role.ADMIN));
        when(mockRepositoryUser.findByEmail("email")).thenReturn(user);

        // Run the test
        final UserDetails result = serviceAppUserUnderTest.loadUserByUsername("email");

        // Verify the results

    }

    @Test
    public void testLoadUserByUsername_ThrowsUsernameNotFoundException() {
        // Setup

        // Configure RepositoryUser.findByEmail(...).
        final User user = new User();
        user.setId(0L);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setActive(false);
        user.setGoogleName("googleName");
        user.setGoogleUsername("googleUsername");
        final Bill bill = new Bill();
        bill.setId(0L);
        bill.setUser(new User());
        bill.setName("name");
        bill.setBalance(new BigDecimal("0.00"));
        user.setBill(List.of(bill));
        user.setRoles(Set.of(Role.ADMIN));
        when(mockRepositoryUser.findByEmail("email")).thenReturn(user);

        // Run the test
        assertThrows(UsernameNotFoundException.class, () -> serviceAppUserUnderTest.loadUserByUsername("email"));
    }

    @Test
    public void testRegisterUser() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setActive(false);
        user.setGoogleName("googleName");
        user.setGoogleUsername("googleUsername");
        final Bill bill = new Bill();
        bill.setId(0L);
        bill.setUser(new User());
        bill.setName("name");
        bill.setBalance(new BigDecimal("0.00"));
        user.setBill(List.of(bill));
        user.setRoles(Set.of(Role.ADMIN));

        when(mockPasswordEncoder.encode("charSequence")).thenReturn("result");

        // Configure Converter.convert(...).
        final ResponseUser responseUser = new ResponseUser();
        responseUser.setEmail("email");
        responseUser.setFirstName("firstName");
        responseUser.setLastName("lastName");
        final Bill bill1 = new Bill();
        bill1.setId(0L);
        final User user1 = new User();
        user1.setId(0L);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setActive(false);
        user1.setGoogleName("googleName");
        user1.setGoogleUsername("googleUsername");
        user1.setBill(List.of(new Bill()));
        user1.setRoles(Set.of(Role.ADMIN));
        bill1.setUser(user1);
        bill1.setName("name");
        bill1.setBalance(new BigDecimal("0.00"));
        responseUser.setBill(List.of(bill1));
        when(mockConverter.convert(any(User.class))).thenReturn(responseUser);

        // Configure RepositoryUser.save(...).
        final User user2 = new User();
        user2.setId(0L);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setActive(false);
        user2.setGoogleName("googleName");
        user2.setGoogleUsername("googleUsername");
        final Bill bill2 = new Bill();
        bill2.setId(0L);
        bill2.setUser(new User());
        bill2.setName("name");
        bill2.setBalance(new BigDecimal("0.00"));
        user2.setBill(List.of(bill2));
        user2.setRoles(Set.of(Role.ADMIN));
        when(mockRepositoryUser.save(any(User.class))).thenReturn(user2);

        // Run the test
        serviceAppUserUnderTest.registerUser(user);

        // Verify the results
    }

    @Test
    public void testRegisterUser_ConverterReturnsNull() {
        // Setup
        final User user = new User();
        user.setId(0L);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setActive(false);
        user.setGoogleName("googleName");
        user.setGoogleUsername("googleUsername");
        final Bill bill = new Bill();
        bill.setId(0L);
        bill.setUser(new User());
        bill.setName("name");
        bill.setBalance(new BigDecimal("0.00"));
        user.setBill(List.of(bill));
        user.setRoles(Set.of(Role.ADMIN));

        when(mockPasswordEncoder.encode("charSequence")).thenReturn("result");
        when(mockConverter.convert(any(User.class))).thenReturn(null);

        // Configure RepositoryUser.save(...).
        final User user1 = new User();
        user1.setId(0L);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setActive(false);
        user1.setGoogleName("googleName");
        user1.setGoogleUsername("googleUsername");
        final Bill bill1 = new Bill();
        bill1.setId(0L);
        bill1.setUser(new User());
        bill1.setName("name");
        bill1.setBalance(new BigDecimal("0.00"));
        user1.setBill(List.of(bill1));
        user1.setRoles(Set.of(Role.ADMIN));
        when(mockRepositoryUser.save(any(User.class))).thenReturn(user1);

        // Run the test
        serviceAppUserUnderTest.registerUser(user);

        // Verify the results
    }
}
