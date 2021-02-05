package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.milov.transactions.dao.converter.ConverterBillToBillResponse;
import ru.milov.transactions.dao.repository.RepositoryBill;
import ru.milov.transactions.dao.response.ResponseBill;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service("/bill")
@RequiredArgsConstructor
public class ServiceAppBill {

    private final RepositoryBill repositoryBill;
    private final ConverterBillToBillResponse converter;

    public List getInfoAboutAllBillsOfUser(ResponseUser responseUser) throws TypeExceptions {
       return repositoryBill.findAllByUser(responseUser)
                .stream()
                .map(converter::convert)
                .collect(toList());
    }

    public ResponseBill createUserBill(Long id, String nameOfBill, BigDecimal balance) {
        Bill userBill = new Bill();

        User user  = new User();
        user.setId(id);

        userBill.setUser(user);
        userBill.setBalance(balance);
        userBill.setName(nameOfBill);
        return converter.convert(repositoryBill.save(userBill));
    }

    public ResponseBill getInfoAboutUserBill (String billName){
        return converter.convert(repositoryBill.findByName(billName));
    }

//    public int countOfBillsMustBeBelowFive(UserDto userDto) throws TypeExceptions {
//        return getInfoAboutAllBillsOfUser(userDto).size();
//    }

//    public void updateUserBill(UserBill userBill) {
//        userBillDao.update(userBill);
//    }
//
//    public boolean deleteUserBill(UserBill userBill) {
//        return userBillDao.delete(userBill.getBill_id());
//    }
}
