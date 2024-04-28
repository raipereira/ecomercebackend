package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Supplier;


import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findBySupplierEmail(String supplierEmail);
}
