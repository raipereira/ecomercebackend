package raiper.miu.cs489.dto.converter.dtoToEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.request.AddressRequest;
import raiper.miu.cs489.model.Address;


@Component
public class ConverterRequestToAddress implements Converter<AddressRequest, Address> {
    @Override
    public Address convert(AddressRequest source) {
        var address = new Address().builder().state(source.state())
                .city(source.city())
                .street(source.street())
                .zipCode(source.zip())
                .build();
        return address;
    }
}
