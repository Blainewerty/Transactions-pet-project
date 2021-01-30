package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServiceUser {

    private int id;
    private String email;
    private String password;
}
