package ru.milov.transactions.service.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class UserBill {

    private Integer user_id;
    private Integer bill_id;
    private String name;
    private BigDecimal balance;

    @Override
    public String toString() {
        return name + '\t' + balance ;
    }
}
