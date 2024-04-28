package raiper.miu.cs489.dto.converter.entityToDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.response.AddressResponse;
import raiper.miu.cs489.model.Address;


@Component
public class ConverterAddressToDto implements Converter<Address, AddressResponse> {
    @Override
    public AddressResponse convert(Address source) {
        var addressResponse = new AddressResponse(source.getAddressId(),
                source.getStreet(), source.getCity(),
                source.getState(), source.getZipCode());
        return addressResponse;
    }
}
