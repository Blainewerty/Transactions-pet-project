package ru.milov.transactions.servlets.controllers;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.milov.transactions.servlets.responce.ConfigurationResponse;

@Configuration
@ComponentScan
@Import(ConfigurationResponse.class)
public class ControllerConfiguration {

}
