package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Shipper;


public interface ShipperRepository extends JpaRepository<Shipper, Integer> {
}
