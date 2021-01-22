package ru.milov.transactions.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.services.ServiceAppBill;
import ru.milov.transactions.service.services.ServiceAppUser;
import ru.milov.transactions.service.services.ServiceConfiguration;
import ru.milov.transactions.view.TypeExceptions;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ServletAuth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        ApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfiguration.class);
        ServiceAppUser serviceAppUser = context.getBean(ServiceAppUser.class);
        ServiceAppBill serviceAppBill = context.getBean(ServiceAppBill.class);
        try {
            UserDto userDto = serviceAppUser.authInApp(email, password);
            if (userDto != null) {
                List<UserBill> billList = serviceAppBill.getInfoAboutAllBillsOfUser(userDto);
                HttpSession session = req.getSession();

                session.setAttribute("billList", billList);
                session.setAttribute("userDto", userDto);

                resp.sendRedirect("/hello");
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
    }
}
