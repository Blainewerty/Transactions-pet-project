package ru.milov.transactions.dao.response;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.milov.transactions.service.entity.UserDto;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ResponseUserBill {

    private Long bill_id;

    private UserDto userDto;

    private String name;

    BigDecimal balance;
}
