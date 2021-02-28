package ru.milov.transactions.service.services

import ru.milov.transactions.converter.ConverterTransactionToTransactionResponse
import ru.milov.transactions.repository.RepositoryTransaction
import ru.milov.transactions.response.ResponseTransaction
import ru.milov.transactions.service.entity.Bill
import ru.milov.transactions.service.entity.Transaction
import spock.lang.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import static org.mockito.Mockito.*

class ServiceAppTransactionTestSpock extends Specification {
    @Mock
    ServiceAppBill serviceAppBill
    @Mock
    RepositoryTransaction repositoryTransaction
    @Mock
    ConverterTransactionToTransactionResponse converter
    @InjectMocks
    ServiceAppTransaction serviceAppTransaction

    def setup() {
        MockitoAnnotations.initMocks(this)
    }

    def "test starting Operation With Bill"() {
        given:
        when(converter.convert(any())).thenReturn(new ResponseTransaction())

        when:
        ResponseTransaction result = serviceAppTransaction.startingOperationWithBill(new Bill(), new Transaction(), "command")

        then:
        result == new ResponseTransaction()
    }

    def "test transfer From Bill To Bill"() {
        given:
        when(converter.convert(any())).thenReturn(new ResponseTransaction())

        def billList = [new Bill().setName("fromBill").setBalance(new BigDecimal(1000)), new Bill().setName("toBill").setBalance(new BigDecimal(1000))]

        when:
        List<ResponseTransaction> result = serviceAppTransaction.transferFromBillToBill(billList , "fromBill", "toBill", 500 as BigDecimal)

        then:
        result.size() == 2
    }


    def "test create Transaction"() {
        given:
        when(converter.convert(any())).thenReturn(new ResponseTransaction())

        when:
        ResponseTransaction result = serviceAppTransaction.createTransaction(new Transaction())

        then:
        result == new ResponseTransaction()
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme