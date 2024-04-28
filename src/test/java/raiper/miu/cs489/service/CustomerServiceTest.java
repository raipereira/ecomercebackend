package raiper.miu.cs489.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import raiper.miu.cs489.model.Customer;
import raiper.miu.cs489.repository.CustomerRepository;
import raiper.miu.cs489.service.impl.CustomerServiceImpl;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    private  List<Customer> customers;

    @BeforeEach
    void setUp() {
        this.customers = new ArrayList<>();
        var customer1 = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123-456-7890")
                .build();
        var customer2 = Customer.builder()
                .firstName("Jane")
                .lastName("Smith")
                .phoneNumber("987-654-3210")
                .build();
        var customer3 = Customer.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .phoneNumber("555-123-4567")
                .build();
        this.customers.add(customer1);
        this.customers.add(customer2);
        this.customers.add(customer3);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllCustomersSuccess() {
        //Give
        given(customerRepository.findAll()).willReturn(this.customers);
        //When
        List<Customer> customers = this.customerService.findAllCustomers();
        //Then
        assertNotNull(customers);
        assertThat(customers.size()).isEqualTo(this.customers.size());
        verify( customerRepository, times(1)).findAll();
    }
}