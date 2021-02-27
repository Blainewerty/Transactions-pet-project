package ru.milov.transactions.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milov.transactions.converter.ConverterBillToBillResponse;
import ru.milov.transactions.repository.RepositoryBill;
import ru.milov.transactions.response.ResponseBill;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ServiceAppBill {

    private final RepositoryBill repositoryBill;
    private final ConverterBillToBillResponse converter;

    @Autowired
    public ServiceAppBill(RepositoryBill repositoryBill, ConverterBillToBillResponse converter) {
        this.repositoryBill = repositoryBill;
        this.converter = converter;
    }

    public List getInfoAboutAllBillsOfUser(User user) {
        return user.getBill()
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

    public ResponseBill getInfoAboutUserBill(User user, String nameOfBill) {
        Optional<Bill> matchingToObject = user.getBill().stream().filter(i-> i.getName().equals(nameOfBill)).findFirst();
        Bill userBill = matchingToObject.get();
        return converter.convert(userBill) ;
    }

    public void updateBill(Bill bill) {
        repositoryBill.save(bill);
    }

    @Transactional
    public void deleteUserBill(User user, String nameOfBill) {
        repositoryBill.deleteByUserAndName(user, nameOfBill);
    }
}
