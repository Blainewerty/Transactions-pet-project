package ru.milov.transactions.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.milov.transactions.servlets.controllers.ControllerConfiguration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletMain extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

//    private final Map<String, Controller> controller;
//
////    public ServletMain(Map<String, Controller> controller){
////        this.controller = controller;
////        this.controller.put("/login", new LoginController());
////    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfiguration.class);

        String uri = req.getRequestURI();
        System.out.println(uri);
        Controller controller = context.getBean(uri, Controller.class);
        try {
            Object request = objectMapper.readValue(req.getInputStream(), controller.getClass());
            Object response = controller.execute(request);
            objectMapper.writeValue(resp.getOutputStream(), response);

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write(e.getMessage());
        }

    }
}