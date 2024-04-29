package raiper.miu.cs489.controller;

import org.springframework.web.bind.annotation.*;
import raiper.miu.cs489.dto.converter.entityToDto.ConverterOrderToDto;
import raiper.miu.cs489.dto.request.OrderRequest;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.OrderService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;
    private ConverterOrderToDto converterOrderToDto;



    public OrderController(OrderService orderService, ConverterOrderToDto converterOrderToDto) {
        this.orderService = orderService;
        this.converterOrderToDto = converterOrderToDto;
    }

    @GetMapping
    public Result getAllOrders() {
        var orders = orderService.getAllOrders().stream().map(order -> converterOrderToDto.convert(order)).toList();
        return new Result(true, StatusCode.SUCCESS, "Get All Success", orders);
    }

    @PostMapping
    public Result placedOrder(@RequestBody OrderRequest orderRequest) {
            orderService.placeOrder(orderRequest);
        return new Result(true, StatusCode.SUCCESS, "Order placed successfully", orderRequest);
    }
}
