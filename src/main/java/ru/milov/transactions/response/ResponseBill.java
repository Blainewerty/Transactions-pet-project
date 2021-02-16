package ru.milov.transactions.response;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.milov.transactions.service.entity.User;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ResponseBill {

    private Long id;

    private User user;

    private String name;

    private BigDecimal balance;

}
