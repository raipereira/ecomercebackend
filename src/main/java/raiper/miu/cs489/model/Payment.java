package raiper.miu.cs489.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private StatusPayment status;
    @Enumerated(EnumType.STRING)
    private TypeOfPayment type;
    private LocalDate paymentDate;
    @OneToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
}
