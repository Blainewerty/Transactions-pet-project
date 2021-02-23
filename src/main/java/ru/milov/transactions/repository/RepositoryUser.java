package ru.milov.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.User;

public interface RepositoryUser extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByGoogleUsername(String email);

}
