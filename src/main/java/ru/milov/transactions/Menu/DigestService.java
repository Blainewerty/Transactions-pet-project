package ru.milov.transactions.Menu;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class DigestService {
    public String digest(String passwrd) {
        return md5Hex(passwrd);
    }
}
