package ru.milov.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("jb@mail.ru")
//                .password("1234")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("USER", "ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/hello").hasAnyRole("USER,ADMIN")
//                .antMatchers(HttpMethod.GET, "/search/**").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
}