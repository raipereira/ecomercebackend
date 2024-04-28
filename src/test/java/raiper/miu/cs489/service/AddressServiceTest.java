package raiper.miu.cs489.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raiper.miu.cs489.exception.AddressNotFoundException;
import raiper.miu.cs489.model.Address;
import raiper.miu.cs489.repository.AddressRepository;
import raiper.miu.cs489.service.impl.AddressServiceImpl;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByIdAddressSuccess() {
        // Given: Arrange input and target. Define the behavior of the mock object addressRepository
        var address = new Address().builder()
                .addressId(1).state("Fairfield").street("1000 4th St").state("Iowa")
                .zipCode("25557").build();
        given(addressRepository.findById(1)).willReturn(Optional.of(address)); // define behavior of mock object
        // when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        // When. Act on the target behavior. When steps should cover the method to be tested
        Address returnAddress = addressService.getByIdAddress(1);

        // Then. Assert expected outcomes.
        assertNotNull(returnAddress);
        assertThat(returnAddress.getAddressId()).isEqualTo(address.getAddressId());
        assertThat(returnAddress.getState()).isEqualTo(address.getState());
        assertThat(returnAddress.getZipCode()).isEqualTo(address.getZipCode());
        assertThat(returnAddress.getStreet()).isEqualTo(address.getStreet());
        verify(addressRepository, times(1)).findById(1);

    }

    @Test
    void getByIdAddressNotFound() {
        //given
        given(addressRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());
        //when
        Throwable thrown = catchThrowable(() -> {
                    Address returnAddress = addressService.getByIdAddress(1);
                });
        //then
        assertThat(thrown)
                .isInstanceOf(AddressNotFoundException.class)
                .hasMessage("Could not find address with id 1 :(" );
        verify(addressRepository, times(1)).findById(1);
    }
}