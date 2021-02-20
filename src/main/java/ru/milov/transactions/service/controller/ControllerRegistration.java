package ru.milov.transactions.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.service.entity.Role;
import ru.milov.transactions.service.entity.User;
import java.util.Collections;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ControllerRegistration {

    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<User> addUser(@RequestBody User userFrom) {
        User user = new User();
        user.setEmail(userFrom.getEmail());
        user.setFirstName(userFrom.getFirstName());
        user.setLastName(userFrom.getLastName());
        user.setPassword(passwordEncoder.encode(userFrom.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        repositoryUser.save(user);

        return ok(user);
    }
}