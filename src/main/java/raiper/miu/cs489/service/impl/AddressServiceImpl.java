package raiper.miu.cs489.service.impl;

import org.springframework.stereotype.Service;
import raiper.miu.cs489.exception.AddressNotFoundException;
import raiper.miu.cs489.model.Address;
import raiper.miu.cs489.repository.AddressRepository;
import raiper.miu.cs489.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService {


    private AddressRepository repo;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.repo = addressRepository;
    }

    @Override
    public Address getByIdAddress(Integer addressId) {
        return repo.findById(addressId).orElseThrow(() -> new AddressNotFoundException(addressId));
    }
}
