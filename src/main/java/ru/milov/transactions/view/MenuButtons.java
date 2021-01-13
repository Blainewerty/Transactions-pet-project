package ru.milov.transactions.view;

import ru.milov.transactions.service.domain.UserDto;

import java.io.IOException;

public interface MenuButtons {

    public void buttonOne();

    public void buttonOne(UserDto userDto);

    public void buttonTwo();

    public void buttonTwo(UserDto userDto);

    public void buttonThree();

    public void buttonThree(UserDto userDto);

    public void buttonFour();

    public void buttonFour(UserDto userDto);

    public void buttonFive();

    public void buttonFive(UserDto userDto);

    public void buttonSix();

    public void buttonSix(UserDto userDto);

    public void buttonClose();

    public void buttonReturn();
}
