package raiper.miu.cs489.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raiper.miu.cs489.dto.request.OrderRequest;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.OrderService;


@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public Result placedOrder(@RequestBody OrderRequest orderRequest) {
            orderService.placeOrder(orderRequest);
        return new Result(true, StatusCode.SUCCESS, "Order placed successfully", orderRequest);
    }
}
