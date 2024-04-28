package raiper.miu.cs489.dto.converter.dtoToEntity;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.request.CustomerRequest;
import raiper.miu.cs489.model.Customer;
import raiper.miu.cs489.model.User;


@Component
public class ConverterRequestToCustomer implements Converter<CustomerRequest, Customer> {

    private ConverterRequestToAddress addressRequestDTO;

    public ConverterRequestToCustomer(ConverterRequestToAddress addressRequestDTO) {
        this.addressRequestDTO = addressRequestDTO;
    }

    @Override
    public Customer convert(CustomerRequest source) {
        var customer =  new Customer().builder()
                .firstName(source.firstName())
                .lastName(source.lastName())
                .phoneNumber(source.phoneNumber())
                .user(new User(source.userName(), source.password(), source.email()))
                .address(addressRequestDTO.convert(source.address()))
                .build();
        return customer;
    }
}
