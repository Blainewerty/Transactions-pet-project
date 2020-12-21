package ru.milov.transactions.Menu;

import java.io.IOException;

public interface MenuFunc {

    void view(String string);

    public void registration() throws IOException;

    public void authentication() throws IOException;

}
