package ru.milov.transactions.dao;


import java.util.List;

public interface Dao <DOMAIN, ID> {

    DOMAIN findById(DOMAIN domain);

    List<DOMAIN> findByAll(DOMAIN user, List<DOMAIN> list);

    DOMAIN insert (DOMAIN domain);

    DOMAIN update (DOMAIN domain);

    boolean delete (ID id);

}
