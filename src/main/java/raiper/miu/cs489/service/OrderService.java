package raiper.miu.cs489.service;


import raiper.miu.cs489.dto.request.OrderRequest;
import raiper.miu.cs489.model.Order;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);

    List<Order> getAllOrders();
}

