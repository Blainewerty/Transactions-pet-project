package ru.milov.transactions.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.milov.transactions.converter.ConverterUserToUserResponse;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.Role;
import ru.milov.transactions.service.entity.User;

import java.util.Collections;

@Service
public class ServiceAppUser implements UserDetailsService {

    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final Converter<User, ResponseUser> converter;

    @Autowired
    public ServiceAppUser(RepositoryUser repositoryUser, PasswordEncoder passwordEncoder, ConverterUserToUserResponse converter) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return repositoryUser.findByEmail(email);
    }


//    public void updateUserInfo(UserDto userDto) {
//        userDao.update(userDto);
//    }

    public void registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        repositoryUser.save(user);
    }


//    public boolean deleteUserFromDb(UserDto userDto){
//        return userDao.delete(userDto.getUser_id());
//    }
}
