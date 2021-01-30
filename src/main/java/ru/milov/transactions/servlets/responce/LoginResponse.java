package ru.milov.transactions.servlets.responce;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.service.entity.UserBill;
import ru.milov.transactions.service.entity.UserDto;
import ru.milov.transactions.service.services.ServiceAppBill;
import ru.milov.transactions.view.TypeExceptions;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginResponse {

    ServiceAppBill serviceAppBill;

    public List<UserBill> showUserBills(UserDto userDto) {
        try {
            return serviceAppBill.getInfoAboutAllBillsOfUser(userDto);
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
        return null;
    }

}
