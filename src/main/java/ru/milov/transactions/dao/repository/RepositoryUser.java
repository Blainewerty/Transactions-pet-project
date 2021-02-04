package ru.milov.transactions.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.User;

public interface RepositoryUser extends JpaRepository<User, Long> {

    User findByEmail(String userDtoEmail);

    User findByEmailAndPassword (String email, String Password);

}
