package raiper.miu.cs489.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import raiper.miu.cs489.model.StatusPayment;
import raiper.miu.cs489.model.TypeOfPayment;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentResponse(Integer paymentId,
         BigDecimal amount, String status, String type, LocalDate paymentDate) {
}
