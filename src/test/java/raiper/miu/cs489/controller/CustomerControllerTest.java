package raiper.miu.cs489.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import raiper.miu.cs489.model.Address;
import raiper.miu.cs489.model.Customer;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.CustomerService;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    private List<Customer> customers;


    @BeforeEach
    void setUp() {
        this.customers = new ArrayList<>();
        var address = Address.builder()
                .street("1000 4th")
                .city("Fairfield").zipCode("52557")
                .state("Iowa").build();
        var customer1 = Customer.builder()
                .customerId(1)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123-456-7890")
                .address(address)
                .build();
        var customer2 = Customer.builder()
                .customerId(2)
                .firstName("Jane")
                .lastName("Smith")
                .phoneNumber("987-654-3210")
                .address(address)
                .build();
        var customer3 = Customer.builder()
                .customerId(3)
                .firstName("Alice")
                .lastName("Johnson")
                .phoneNumber("555-123-4567")
                .address(address)
                .build();
        this.customers.add(customer1);
        this.customers.add(customer2);
        this.customers.add(customer3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCustomersSuccess() throws Exception {
        //Given
        given(this.customerService.findAllCustomers()).willReturn(this.customers);

        //When and Then
        this.mockMvc.perform(get("/api/v1/customers"))
                .andExpectAll(
                        jsonPath("$.flag").value(true),
                        jsonPath("$.code").value(StatusCode.SUCCESS),
                        jsonPath("$.message").value("Find All Success"),
                        jsonPath("$.data", Matchers.hasSize(this.customers.size())),
                        jsonPath("$.data[0].customerId").value(1),
                        jsonPath("$.data[0].firstName").value("John")
                );
    }
}