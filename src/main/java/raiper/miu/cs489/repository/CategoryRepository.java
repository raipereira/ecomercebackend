package raiper.miu.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raiper.miu.cs489.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String productName);
}
