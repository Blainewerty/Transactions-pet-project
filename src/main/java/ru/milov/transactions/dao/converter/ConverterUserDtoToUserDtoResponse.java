package ru.milov.transactions.dao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.dao.response.ResponseUserDto;
import ru.milov.transactions.service.entity.UserDto;

@Component
public class ConverterUserDtoToUserDtoResponse implements Converter<UserDto, ResponseUserDto> {

    @Override
    public ResponseUserDto convert(UserDto userDto) {
        return new ResponseUserDto()
                .setUser_id(userDto.getUser_id())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setTotalBalance(userDto.getTotalBalance());
    }
}
