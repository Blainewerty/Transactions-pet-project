package ru.milov.transactions.dao;

import java.util.List;

public interface Dao <DOMAIN, ID> {
    DOMAIN findById(DOMAIN domain, DOMAIN domainbill);

    List<DOMAIN> findByAll(DOMAIN user,DOMAIN userbill ,List<DOMAIN> list);

    DOMAIN insert (DOMAIN domain);

    DOMAIN update (DOMAIN domain, DOMAIN domainbill);

    boolean delete (ID id);
}
