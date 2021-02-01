package ru.milov.transactions.dao;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//
//@ComponentScan
//@Configuration
//public class DaoConfiguration {
//
//    @Bean
//    public DataSource dataSource(Environment env) {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDriverClassName("org.postgresql.Driver");
//        hikariDataSource.setJdbcUrl(env.getProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/postgres"));
//        hikariDataSource.setUsername(env.getProperty("jdbcUsername", "postgres"));
//        hikariDataSource.setPassword(env.getProperty("jdbcPassword", "admin"));
//        return hikariDataSource;
//    }
//}
