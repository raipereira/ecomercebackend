package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
