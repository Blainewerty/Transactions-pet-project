package ru.milov.transactions.service;

import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceApp {

    SQLActions sqlActions = new SQLActions();

    public UserDto checkIfUserInDb(String email, String password) throws TypeExceptions {
        UserDto userDto = ServiceFactory.getServiceSecurity().checkIfUserInDb(email, password);
        if (userDto != null) {
            return userDto;
        }else throw new TypeExceptions("Can't register user with this email!");
    }

    public UserDto registerUser(UserDto userDto){
        return sqlActions.insertUserToDb(userDto);
    }

    public UserDto authInApp(String email, String password) throws TypeExceptions {
        UserDto userDto = ServiceFactory.getServiceSecurity().auth(email, password);
        if (userDto != null) {
            return sqlActions.fillingUserInfo(userDto);
        }else throw new TypeExceptions("There is no such user in DB!");
    }

    public void addBillToDB(UserBill userBill) throws TypeExceptions{
        if (userBill != null) {
            sqlActions.createBill(userBill);
        }else throw new TypeExceptions("Problem with Bill Info!");
    }

    public void addOperationToDB(Transaction operation) throws TypeExceptions{
        if (operation != null) {
            sqlActions.addOperationOfBillToSQL(operation);
        }else throw new TypeExceptions("Problem with Operation Info!");
    }

    public  void getInfoAboutUser (UserDto userDto) throws TypeExceptions{
        if (userDto != null) {
            UserBill userBill = new UserBill();
            userBill.setId(userDto.getId());

            sqlActions.findInfoAboutUsersBillsInDb(userBill);
        }else throw new TypeExceptions("Problem with User Info!");
    }

    public void createUserBill (UserDto userDto, String nameOfBill, int balance){
        UserBill userBill = new UserBill();

        userBill.setId(userDto.getId());
        userBill.setBalance(balance);
        userBill.setName(nameOfBill);


    }

    public void startingOperationWithBill(UserDto userDto, String  nameOfTransaction, int valueOfOperation, String command){

        Transaction transaction = new Transaction();
        transaction.setId(userDto.getId());
        transaction.setNameOfTransaction(nameOfTransaction);
        transaction.setLastTransaction(valueOfOperation);

        try {
            addOperationToDB(transaction);
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
        if (command.equals("1")) {
            //userBill.setBalance(userBill.getBalance() + transaction.getLastTransaction());
        }
        if (command.equals("2")) {
            //userBill.setBalance(userBill.getBalance() - transaction.getLastTransaction());
        }
        if (command.equals("3")) {
        }
        
        
    }

}
