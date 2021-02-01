package ru.milov.transactions.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class UserBillResponse {

    private Long bill_id;

    private Long user_id;

    private String name;

    BigDecimal balance;
}
