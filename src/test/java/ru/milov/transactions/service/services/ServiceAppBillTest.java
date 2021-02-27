package ru.milov.transactions.service.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.milov.transactions.converter.ConverterBillToBillResponse;
import ru.milov.transactions.repository.RepositoryBill;
import ru.milov.transactions.response.ResponseBill;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ServiceAppBillTest {

    @Mock
    RepositoryBill repositoryBill;
    @Mock
    ConverterBillToBillResponse converter;
    @InjectMocks
    ServiceAppBill serviceAppBill;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreateUserBill() throws Exception {
        when(converter.convert(any())).thenReturn(new ResponseBill());

        ResponseBill result = serviceAppBill.createUserBill(new User(), new Bill());
        Assert.assertEquals(new ResponseBill(), result);
    }


    @Test
    public void testUpdateBill() throws Exception {
        serviceAppBill.updateBill(new Bill());
    }

    @Test
    public void testDeleteUserBill() throws Exception {
        serviceAppBill.deleteUserBill(new User(), "nameOfBill");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme