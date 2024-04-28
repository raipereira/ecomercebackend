package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
