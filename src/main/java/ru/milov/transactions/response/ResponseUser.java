package ru.milov.transactions.response;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.milov.transactions.service.entity.Bill;
import java.util.List;

@Data
@Accessors(chain = true)
public class ResponseUser {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private List<Bill> bill;
}
