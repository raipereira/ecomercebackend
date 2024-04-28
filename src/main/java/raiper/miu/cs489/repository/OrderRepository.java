package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
}
