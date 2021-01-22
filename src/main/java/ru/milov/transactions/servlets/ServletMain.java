package ru.milov.transactions.servlets;

import ru.milov.transactions.service.domain.UserDto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserDto userDto = (UserDto) req.getSession().getAttribute("userDto");
        if (userDto == null) {
            resp.sendRedirect("/auth");
        } else {
            resp.sendRedirect("/bills");
        }
    }
}
