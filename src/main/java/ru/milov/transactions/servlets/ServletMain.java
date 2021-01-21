package ru.milov.transactions.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        if (userId == null){
            resp.sendRedirect("/auth");
        } else {
            writer.print("Your login is " + userId);
        }
    }
}
