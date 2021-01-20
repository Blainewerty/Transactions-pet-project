package ru.milov.transactions.view;

import org.springframework.context.annotation.*;
import ru.milov.transactions.service.services.ServiceConfiguration;

@ComponentScan
@Import(ServiceConfiguration.class)
@Configuration
public class MenuConfiguration {

}
