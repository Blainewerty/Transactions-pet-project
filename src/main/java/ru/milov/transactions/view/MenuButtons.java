package ru.milov.transactions.view;

public interface MenuButtons <DOMAIN>{

    void start();

    void start(DOMAIN domain);

    void buttonOne();

    void buttonOne(DOMAIN domain);

    void buttonTwo();

    void buttonTwo(DOMAIN domain);

    void buttonThree(DOMAIN domain);

    void buttonFour(DOMAIN domain);

    void buttonFive(DOMAIN domain);

    void buttonClose();

}
