package raiper.miu.cs489.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raiper.miu.cs489.model.Customer;
import raiper.miu.cs489.model.Role;
import raiper.miu.cs489.repository.CustomerRepository;
import raiper.miu.cs489.repository.RoleRepository;
import raiper.miu.cs489.repository.UserRepository;
import raiper.miu.cs489.service.CustomerService;


import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }


}
