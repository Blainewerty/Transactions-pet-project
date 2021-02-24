package ru.milov.transactions.response;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.milov.transactions.service.entity.Bill;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class ResponseTransaction {

    private Long transaction_id;

    private Timestamp date;

    private Bill bill;

    private String nameOfTransaction;

    private BigDecimal valueOfTransaction;

}
