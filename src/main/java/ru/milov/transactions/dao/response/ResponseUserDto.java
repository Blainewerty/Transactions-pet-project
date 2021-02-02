package ru.milov.transactions.dao.response;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ResponseUserDto {

    private Long user_id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private BigDecimal totalBalance;
}
