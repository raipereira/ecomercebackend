package raiper.miu.cs489.dto.converter.dtoToEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.UserDto;
import raiper.miu.cs489.model.User;
@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto source) {
        var user = new User();
        user.setUsername(source.username());
        user.setEmail(source.username());
        return user;
    }
}