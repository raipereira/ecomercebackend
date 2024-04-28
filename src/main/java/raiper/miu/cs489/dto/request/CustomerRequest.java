package raiper.miu.cs489.dto.request;

public record CustomerRequest(
                              String firstName,
                              String lastName,
                              String phoneNumber,
                              String email,
                              String userName,
                              String password,
                              AddressRequest address
                               ) {
}
