package ru.milov.transactions.service.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserDto {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int totalBalance;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
