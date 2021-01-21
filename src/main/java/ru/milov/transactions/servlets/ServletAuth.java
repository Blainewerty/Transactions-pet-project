package ru.milov.transactions.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletAuth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("alex".equals(username) && "password".equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("user_id", 13);
        }else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
