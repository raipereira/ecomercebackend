package raiper.miu.cs489.dto.response;

import java.time.LocalDateTime;

public record OrderResponse(Integer orderId,
                            LocalDateTime orderDate,
                            //Integer quantity,
                            //double price,
                            CustomerResponse customer
                           // PaymentResponse payment,
                           // ShipperResponse shipper
) {
}
