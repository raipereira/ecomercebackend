package raiper.miu.cs489.dto.converter.entityToDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.UserDto;
import raiper.miu.cs489.model.User;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        // We are not setting password in DTO.
        final UserDto userDto = new UserDto(source.getUserId(),
                source.getUsername(),
                source.getEmail());

        return userDto;
    }

}