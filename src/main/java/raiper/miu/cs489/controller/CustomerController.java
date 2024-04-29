package raiper.miu.cs489.controller;

import org.springframework.web.bind.annotation.*;
import raiper.miu.cs489.dto.converter.dtoToEntity.ConverterRequestToCustomer;
import raiper.miu.cs489.dto.converter.entityToDto.ConverterCustomerToDto;
import raiper.miu.cs489.dto.request.CustomerRequest;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.CustomerService;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;
    private ConverterCustomerToDto converterCustomerToCustomerDTO;


    public CustomerController(CustomerService customerService,
                              ConverterCustomerToDto toCustomerDTO) {
        this.customerService = customerService;
        this.converterCustomerToCustomerDTO = toCustomerDTO;
    }

    @GetMapping()
    public Result getAllCustomers() {
        var customers = customerService.findAllCustomers();
        var customersResponse =  customers.stream().map(customer -> converterCustomerToCustomerDTO.convert(customer)).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", customersResponse);
    }

}
