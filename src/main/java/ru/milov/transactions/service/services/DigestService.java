package ru.milov.transactions.service.services;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class DigestService {
    public String digest(String passwrd) {
        return md5Hex(passwrd);
    }
}