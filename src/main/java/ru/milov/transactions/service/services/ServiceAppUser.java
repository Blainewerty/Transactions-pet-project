package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.converter.ConverterUserToUserResponse;
import ru.milov.transactions.dao.repository.RepositoryUser;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.User;

@Service
@RequiredArgsConstructor
public class ServiceAppUser {

    @Autowired
    RepositoryUser repositoryUser;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ConverterUserToUserResponse converter;

    public User checkIfUserInDb(String email) throws TypeExceptions {
        User user = repositoryUser.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new TypeExceptions("Can't register user with this email!");
        }
    }

//    public void updateUserInfo(UserDto userDto) {
//        userDao.update(userDto);
//    }

    public ResponseUser registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return converter.convert(repositoryUser.save(user));
    }

    public ResponseUser authInApp(String email, String password) throws TypeExceptions {
        User user = checkIfUserInDb(email);
        if (user != null) {
            return converter.convert(repositoryUser.findByEmailAndPassword(user.getEmail(), user.getPassword()));
        } else throw new TypeExceptions("There is no such user in DB!");
    }

//    public boolean deleteUserFromDb(UserDto userDto){
//        return userDao.delete(userDto.getUser_id());
//    }
}
