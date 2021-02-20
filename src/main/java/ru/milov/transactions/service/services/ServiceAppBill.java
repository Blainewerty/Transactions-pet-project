package ru.milov.transactions.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milov.transactions.converter.ConverterBillToBillResponse;
import ru.milov.transactions.repository.RepositoryBill;
import ru.milov.transactions.response.ResponseBill;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ServiceAppBill{

    private final RepositoryBill repositoryBill;
    private final ServiceAppUser serviceAppUser;
    private final ConverterBillToBillResponse converter;

    @Autowired
    public ServiceAppBill(RepositoryBill repositoryBill, ServiceAppUser serviceAppUser, ConverterBillToBillResponse converter) {
        this.repositoryBill = repositoryBill;
        this.serviceAppUser = serviceAppUser;
        this.converter = converter;
    }

    public List getInfoAboutAllBillsOfUser(Long id) {
       return repositoryBill.findAllByUser_id(id)
                .stream()
                .map(converter::convert)
                .collect(toList());
    }

    public ResponseBill createUserBill(User user, Bill newBill) {
        Bill bill = new Bill();

        bill.setUser(user);
        bill.setName(newBill.getName());
        bill.setBalance(newBill.getBalance());



        return converter.convert(repositoryBill.save(bill));
    }

    public ResponseBill getInfoAboutUserBill (Long id, String nameOfBill){
        return converter.convert(repositoryBill.findByUser_idAndName(id, nameOfBill));
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
