package ru.milov.transactions.dao;


import ru.milov.transactions.service.domain.Transaction;

import java.sql.Connection;
import java.util.List;

public interface Dao <DOMAIN, ID> {

    DOMAIN findById(DOMAIN domain);

    List<DOMAIN> findByAll(DOMAIN user, List<DOMAIN> list);

    DOMAIN insert (DOMAIN domain);

    DOMAIN insert(DOMAIN domain, Connection connection);

    DOMAIN update (DOMAIN domain);

    boolean delete (ID id);

}
