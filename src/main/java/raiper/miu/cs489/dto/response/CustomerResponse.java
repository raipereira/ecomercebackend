package raiper.miu.cs489.dto.response;

public record CustomerResponse(Integer customerId,
                               String firstName,
                               String lastName,
                               String phoneNumber,
                               String email,
                               AddressResponse address
                               ) {
}
