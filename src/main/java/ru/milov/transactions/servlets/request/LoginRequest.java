package ru.milov.transactions.servlets.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
