package ru.milov.transactions.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import ru.milov.transactions.service.entity.Bill;
import java.util.List;

@Data
@Component
@Accessors(chain = true)
public class ResponseUser {

    private String email;

    private String firstName;

    private String lastName;

    private List<Bill> bill;
}
