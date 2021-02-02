package ru.milov.transactions.dao.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ResponseTransaction {

    private Long transaction_id;

    private Long user_id;

    private Long bill_id;

    private String date;

    private String nameOfTransaction;

    private BigDecimal valueOfTransaction;

}
