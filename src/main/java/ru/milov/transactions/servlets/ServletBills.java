package ru.milov.transactions.servlets;

import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletBills extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();

        List<UserBill> billList = (List<UserBill>) req.getSession().getAttribute("billList");
        UserDto userDto = (UserDto) req.getSession().getAttribute("userDto");
        if (billList == null) {
            resp.sendRedirect("/auth");
        } else {
            writer.println("Hello " + userDto.getFirstName() + "\n" +
                    "Your balance: " + userDto.getTotalBalance());
            writer.println("Your bill list: \n");
            for (Object bill : billList) {
                writer.println(bill);
            }
        }
    }
}