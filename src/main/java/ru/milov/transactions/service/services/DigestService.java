package ru.milov.transactions.service.services;

import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@Service
public class DigestService {
    public String digest(String passwrd) {
        return md5Hex(passwrd);
    }
}
