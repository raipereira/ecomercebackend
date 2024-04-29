package raiper.miu.cs489.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import raiper.miu.cs489.dto.converter.dtoToEntity.UserDtoToUserConverter;
import raiper.miu.cs489.dto.converter.entityToDto.UserToUserDtoConverter;
import raiper.miu.cs489.model.MyUserDetails;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private UserToUserDtoConverter userToUserDtoConverter;

    private JwtProvider jwtProvider;
    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String, Object> createLogInfo(Authentication authentication) {
        //create user info
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        var userDto = userToUserDtoConverter.convert(userDetails.getUser());
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
