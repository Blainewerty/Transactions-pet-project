package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milov.transactions.converter.ConverterUserToUserResponse;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.User;

@Service
@RequiredArgsConstructor
public class ServiceAppUser {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private ConverterUserToUserResponse converter;
    @Autowired
    private DigestService digestService;

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
        user.setPassword(digestService.digest(user.getPassword()));
        return converter.convert(repositoryUser.save(user));
    }

    public ResponseUser authInApp(User user) {
        try {
            if (checkIfUserInDb(user.getEmail()) != null) {
                return converter.convert(repositoryUser.findByEmailAndPassword(user.getEmail(),
                        digestService.digest(user.getPassword())));
            } else
                throw new TypeExceptions("There is no such user in DB!");
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
        return null;
    }

//    public boolean deleteUserFromDb(UserDto userDto){
//        return userDao.delete(userDto.getUser_id());
//    }
}
