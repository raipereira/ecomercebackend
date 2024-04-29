package raiper.miu.cs489.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raiper.miu.cs489.dto.converter.dtoToEntity.ConverterRequestToCustomer;
import raiper.miu.cs489.dto.converter.entityToDto.ConverterCustomerToDto;
import raiper.miu.cs489.dto.request.CustomerRequest;
import raiper.miu.cs489.model.User;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.UserService;

@RestController
//@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private ConverterRequestToCustomer requestDtoToUser;
    private ConverterCustomerToDto converterUserToUserDTO;
    public UserController(UserService userService, ConverterRequestToCustomer requestDtoToUser,
                          ConverterCustomerToDto converterUserToUserDTO) {
        this.userService = userService;
        this.requestDtoToUser = requestDtoToUser;
        this.requestDtoToUser = requestDtoToUser;
        this.converterUserToUserDTO = converterUserToUserDTO;

    }



    public Result RegisterUser(@RequestBody CustomerRequest userRequest) {
        var newUser =  userService.registerUser(requestDtoToUser.convert(userRequest));
        var userResponse = converterUserToUserDTO.convert(newUser);
        return new Result(true, StatusCode.SUCCESS, "User Save Success", userResponse);

    }
}
