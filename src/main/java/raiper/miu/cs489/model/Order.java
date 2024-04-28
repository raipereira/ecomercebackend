package raiper.miu.cs489.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(nullable = false)
    @NotNull(message = "Order Date is Required")
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToOne(mappedBy = "order")
    private Payment payment;
    @OneToOne(mappedBy = "order")
    private Shipper shipper;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();


}
