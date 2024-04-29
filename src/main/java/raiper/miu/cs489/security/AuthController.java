package raiper.miu.cs489.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import raiper.miu.cs489.dto.converter.dtoToEntity.ConverterRequestToCustomer;
import raiper.miu.cs489.dto.converter.entityToDto.ConverterCustomerToDto;
import raiper.miu.cs489.dto.request.CustomerRequest;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;
    private AuthService authService;
    private ConverterRequestToCustomer requestDtoToUser;
    private ConverterCustomerToDto converterUserToUserDTO;
    public AuthController(AuthService authService, ConverterRequestToCustomer requestDtoToUser,
                          ConverterCustomerToDto converterUserToUserDTO,
                          UserService userService) {
        this.authService = authService;
        this.requestDtoToUser = requestDtoToUser;
        this.converterUserToUserDTO = converterUserToUserDTO;
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result RegisterUser(@RequestBody CustomerRequest userRequest) {
        var newUser =  userService.registerUser(requestDtoToUser.convert(userRequest));
        var userResponse = converterUserToUserDTO.convert(newUser);
        return new Result(true, StatusCode.SUCCESS, "User Save Success", userResponse);

    }


    @PostMapping("/login")
    public Result login(Authentication authentication) {
        LOGGER.debug("Authenticating user {}", authentication.getName());
        return new Result(true, StatusCode.SUCCESS, "User info and Json Web Token",
                this.authService.createLogInfo(authentication));

    }
}
