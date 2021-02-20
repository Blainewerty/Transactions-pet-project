package ru.milov.transactions.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.milov.transactions.converter.ConverterUserToUserResponse;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.User;

@Service
public class ServiceAppUser implements UserDetailsService {

    private final RepositoryUser repositoryUser;
    private final Converter<User, ResponseUser> converter;
    private final DigestService digestService;

    @Autowired
    public ServiceAppUser(RepositoryUser repositoryUser, ConverterUserToUserResponse converter, DigestService digestService) {
        this.repositoryUser = repositoryUser;
        this.converter = converter;
        this.digestService = digestService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return repositoryUser.findByEmail(email);
    }


//    public void updateUserInfo(UserDto userDto) {
//        userDao.update(userDto);
//    }

    public ResponseUser registerUser(User user) {
        user.setPassword(digestService.digest(user.getPassword()));
        return converter.convert(repositoryUser.save(user));
    }


//    public boolean deleteUserFromDb(UserDto userDto){
//        return userDao.delete(userDto.getUser_id());
//    }
}
