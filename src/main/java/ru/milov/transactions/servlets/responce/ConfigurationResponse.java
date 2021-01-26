package ru.milov.transactions.servlets.responce;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.milov.transactions.service.services.ServiceConfiguration;

@Configuration
@ComponentScan
@Import(ServiceConfiguration.class)
public class ConfigurationResponse {
}
