package raiper.miu.cs489.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(Integer id,
                      @NotEmpty(message = "username is required.")
                      String username,
                      @NotEmpty(message = "username is required.")
                      String email) {

                      }
