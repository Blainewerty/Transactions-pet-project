package ru.milov.transactions.service.services;

import ru.milov.transactions.response.ResponseBill;

import java.math.BigDecimal;
import java.security.DomainCombiner;

public interface ServiceAction<DOMAIN, String, BigDecimal> {

    DOMAIN create(String string, BigDecimal balance);

    void insert();

    DOMAIN getInfo();

    void delete();

}
