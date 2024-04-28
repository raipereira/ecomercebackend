package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
