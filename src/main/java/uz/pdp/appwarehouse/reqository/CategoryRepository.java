package uz.pdp.appwarehouse.reqository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
