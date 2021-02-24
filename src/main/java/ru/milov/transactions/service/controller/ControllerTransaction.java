package ru.milov.transactions.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.response.ResponseTransaction;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.Transaction;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppTransaction;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/transaction")
public class ControllerTransaction {

    private final ServiceAppTransaction serviceAppTransaction;

    public ControllerTransaction(ServiceAppTransaction serviceAppTransaction) {
        this.serviceAppTransaction = serviceAppTransaction;
    }

    @PostMapping("/{command}/{nameOfBill}")
    public ResponseEntity<ResponseTransaction> updateBill(@PathVariable("nameOfBill") String nameOfBill,
                                                          @PathVariable("command") String command,
                                                          @RequestBody Transaction transaction) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Bill> matchingObject = user.getBill().stream().filter(i -> i.getName().equals(nameOfBill)).findFirst();
        Bill bill = matchingObject.orElse(null);
        if (bill != null) {
            bill.setUser(user);
            return ok(serviceAppTransaction.startingOperationWithBill(bill,
                    transaction, command));
        } else {
            return null;
        }
    }

    @PostMapping("/transfer/{nameFromBill}")
    public ResponseEntity<List<ResponseTransaction>> transfer(@PathVariable("nameFromBill")
                                                                      String nameFromBill,
                                                              @RequestBody Bill bill) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(serviceAppTransaction.transferFromBillToBill(user.getBill(), nameFromBill, bill.getName(), bill.getBalance()));
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<ResponseTransaction>> getTransactions() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(serviceAppTransaction.getInfoAboutAllUsersTransactions(user));
    }

    @GetMapping("/{nameOfBill}")
    public ResponseEntity<List<ResponseTransaction>> getBillTransactions(@PathVariable("nameOfBill")
                                                                                 String nameOfBill) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(serviceAppTransaction.getInfoAboutBillTransactions(user, nameOfBill));
    }
}
