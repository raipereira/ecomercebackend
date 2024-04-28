package raiper.miu.cs489.controller;


import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raiper.miu.cs489.dto.response.AddressResponse;
import raiper.miu.cs489.model.Address;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.AddressService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private AddressService addressService;
    private ConversionService conversionService;

    public AddressController(AddressService addressService, ConversionService conversionService) {
        this.addressService = addressService;
        this.conversionService = conversionService;
    }

    @GetMapping("/")
    public List<Address> getAllAddresses() {
        return null;
    }
    @GetMapping("/{addressId}")
    public Result getAddressById(@PathVariable Integer addressId) {
        var address = addressService.getByIdAddress(addressId);
        var addressResponse = conversionService.convert(address, AddressResponse.class);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", addressResponse);
    }
}
