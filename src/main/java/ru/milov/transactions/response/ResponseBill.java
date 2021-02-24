package ru.milov.transactions.response;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ResponseBill {

    private Long id;

    private String name;

    private BigDecimal balance;

}
