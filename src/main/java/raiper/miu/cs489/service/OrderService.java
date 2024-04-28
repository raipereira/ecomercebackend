package raiper.miu.cs489.service;


import raiper.miu.cs489.dto.request.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);
}

