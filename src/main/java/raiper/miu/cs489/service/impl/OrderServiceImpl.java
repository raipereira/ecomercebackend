package raiper.miu.cs489.service.impl;

import org.springframework.stereotype.Service;
import raiper.miu.cs489.dto.request.OrderRequest;
import raiper.miu.cs489.model.Order;
import raiper.miu.cs489.model.OrderDetail;
import raiper.miu.cs489.model.OrderDetailId;
import raiper.miu.cs489.repository.*;
import raiper.miu.cs489.service.OrderService;


import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class OrderServiceImpl implements OrderService {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    public OrderServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository,
                            OrderRepository orderRepository, PaymentRepository paymentRepository,
                            UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;

    }


    @Override
    public void placeOrder(OrderRequest orderRequest) {
        var user = userRepository.findByEmail(orderRequest.userEmail());
      //  if(user.isPresent()){
            var customer = user.get().getCustomer();
       // }
        var product = productRepository.findById(orderRequest.productId()).get();

        var order = new Order().builder()
                .orderDate(LocalDateTime.now())
                .customer(customer)
                .build();
        var orderDetail = new OrderDetail().builder()
                .price(orderRequest.productPrice())
                .quantity(orderRequest.quantity())
                .id(new OrderDetailId(order, product))
                .build();
        order.setOrderDetails(Collections.singletonList(orderDetail));

       orderRepository.save(order);
    }
}
