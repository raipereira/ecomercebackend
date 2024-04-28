package raiper.miu.cs489.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raiper.miu.cs489.exception.AddressNotFoundException;
import raiper.miu.cs489.model.Address;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.AddressService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    private List<Address> addressList;

    @BeforeEach
    void setUp() {
        this.addressList = new ArrayList<>();
        var address1 = Address.builder().addressId(1)
                .street("1000 4th").city("Fairfield").zipCode("52557")
                .state("Iowa").build();

        var address2 = Address.builder().addressId(2)
                .street("456 Elm St").city("Las Vegas").zipCode("52557")
                .state("Nevada").build();

        var address3 = Address.builder().addressId(3)
                .street("123 Main St").city("San Diego").zipCode("12345")
                .state("California").build();
        this.addressList.addAll(Arrays.asList(address1, address2, address3));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAddressByIdSuccess() throws Exception {
        //given
        given(this.addressService.getByIdAddress(1)).willReturn(this.addressList.get(0));
        //when and then
        mockMvc.perform(get("/api/v1/addresses/1").accept(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.flag").value(true),
                        jsonPath("$.code").value(StatusCode.SUCCESS),
                        jsonPath("$.message").value("Find One Success"),
                        jsonPath("$.data.addressId").value(1),
                        jsonPath("$.data.city").value("Fairfield")
                );
    }
    @Test
    void getAddressByIdFailure() throws Exception {
        given(this.addressService.getByIdAddress(1)).willThrow(new AddressNotFoundException(1));
        mockMvc.perform(get("/api/v1/addresses/1").accept(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.flag").value(false),
                        jsonPath("$.code").value(StatusCode.NOT_FOUND),
                        jsonPath("$.message").value("Could not find address with id 1 :("),
                        jsonPath("$.data").isEmpty()
                );
    }
}