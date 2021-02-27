package ru.milov.transactions.service.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milov.transactions.converter.ConverterBillToBillResponse;
import ru.milov.transactions.repository.RepositoryBill;
import ru.milov.transactions.response.ResponseBill;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.Role;
import ru.milov.transactions.service.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ServiceAppBillTest {

    @Mock
    private RepositoryBill mockRepositoryBill;
    @Mock
    private ServiceAppUser mockServiceAppUser;
    @Mock
    private ConverterBillToBillResponse mockConverter;

    private ServiceAppBill serviceAppBillUnderTest;

    @Before
    public void setUp() {
        serviceAppBillUnderTest = new ServiceAppBill(mockRepositoryBill, mockServiceAppUser, mockConverter);
    }

    @Test
    public void testGetInfoAboutAllBillsOfUser() {
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

        // Configure ConverterBillToBillResponse.convert(...).
        final ResponseBill responseBill = new ResponseBill();
        responseBill.setId(0L);
        responseBill.setName("name");
        responseBill.setBalance(new BigDecimal("0.00"));
        when(mockConverter.convert(any(Bill.class))).thenReturn(responseBill);

        // Run the test
        final List result = serviceAppBillUnderTest.getInfoAboutAllBillsOfUser(user);

        // Verify the results
    }

    @Ignore
    @Test
    public void testGetInfoAboutAllBillsOfUser_ConverterBillToBillResponseReturnsNull() {
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

        when(mockConverter.convert(any(Bill.class))).thenReturn(null);

        // Run the test
        final List result = serviceAppBillUnderTest.getInfoAboutAllBillsOfUser(user);

        // Verify the results
    }

    @Test
    public void testCreateUserBill() {
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

        final Bill newBill = new Bill();
        newBill.setId(0L);
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
        newBill.setUser(user1);
        newBill.setName("name");
        newBill.setBalance(new BigDecimal("0.00"));

        final ResponseBill expectedResult = new ResponseBill();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setBalance(new BigDecimal("0.00"));

        // Configure ConverterBillToBillResponse.convert(...).
        final ResponseBill responseBill = new ResponseBill();
        responseBill.setId(0L);
        responseBill.setName("name");
        responseBill.setBalance(new BigDecimal("0.00"));
        when(mockConverter.convert(any(Bill.class))).thenReturn(responseBill);

        // Configure RepositoryBill.save(...).
        final Bill bill1 = new Bill();
        bill1.setId(0L);
        final User user2 = new User();
        user2.setId(0L);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setActive(false);
        user2.setGoogleName("googleName");
        user2.setGoogleUsername("googleUsername");
        user2.setBill(List.of(new Bill()));
        user2.setRoles(Set.of(Role.ADMIN));
        bill1.setUser(user2);
        bill1.setName("name");
        bill1.setBalance(new BigDecimal("0.00"));
        when(mockRepositoryBill.save(any(Bill.class))).thenReturn(bill1);

        // Run the test
        final ResponseBill result = serviceAppBillUnderTest.createUserBill(user, newBill);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateUserBill_ConverterBillToBillResponseReturnsNull() {
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

        final Bill newBill = new Bill();
        newBill.setId(0L);
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
        newBill.setUser(user1);
        newBill.setName("name");
        newBill.setBalance(new BigDecimal("0.00"));

        final ResponseBill expectedResult = new ResponseBill();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setBalance(new BigDecimal("0.00"));

        when(mockConverter.convert(any(Bill.class))).thenReturn(null);

        // Configure RepositoryBill.save(...).
        final Bill bill1 = new Bill();
        bill1.setId(0L);
        final User user2 = new User();
        user2.setId(0L);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setActive(false);
        user2.setGoogleName("googleName");
        user2.setGoogleUsername("googleUsername");
        user2.setBill(List.of(new Bill()));
        user2.setRoles(Set.of(Role.ADMIN));
        bill1.setUser(user2);
        bill1.setName("name");
        bill1.setBalance(new BigDecimal("0.00"));
        when(mockRepositoryBill.save(any(Bill.class))).thenReturn(bill1);

        // Run the test
        final ResponseBill result = serviceAppBillUnderTest.createUserBill(user, newBill);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Ignore
    @Test
    public void testGetInfoAboutUserBill() {
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

        final ResponseBill expectedResult = new ResponseBill();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setBalance(new BigDecimal("0.00"));

        // Run the test
        final ResponseBill result = serviceAppBillUnderTest.getInfoAboutUserBill(user, "nameOfBill");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateBill() {
        // Setup
        final Bill bill = new Bill();
        bill.setId(0L);
        final User user = new User();
        user.setId(0L);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setActive(false);
        user.setGoogleName("googleName");
        user.setGoogleUsername("googleUsername");
        user.setBill(List.of(new Bill()));
        user.setRoles(Set.of(Role.ADMIN));
        bill.setUser(user);
        bill.setName("name");
        bill.setBalance(new BigDecimal("0.00"));

        // Configure RepositoryBill.save(...).
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
        when(mockRepositoryBill.save(any(Bill.class))).thenReturn(bill1);

        // Run the test
        serviceAppBillUnderTest.updateBill(bill);

        // Verify the results
    }

    @Test
    public void testDeleteUserBill() {
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

        // Run the test
        serviceAppBillUnderTest.deleteUserBill(user, "nameOfBill");

        // Verify the results
        verify(mockRepositoryBill).deleteByUserAndName(any(User.class), eq("nameOfBill"));
    }
}
