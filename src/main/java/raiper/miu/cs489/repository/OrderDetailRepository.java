package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.OrderDetail;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
