package ru.milov.transactions.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.milov.transactions.service.entity.UserDto;

public interface RepositoryUserDto extends JpaRepository<UserDto, Long> {

    UserDto findByEmail(String userDtoEmail);


    UserDto findByEmailAndPassword (String email, String Password);
}
