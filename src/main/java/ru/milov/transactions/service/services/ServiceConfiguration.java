package ru.milov.transactions.service.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.milov.transactions.dao.DaoConfiguration;

@ComponentScan
@Import(DaoConfiguration.class)
@Configuration
public class ServiceConfiguration {

}
