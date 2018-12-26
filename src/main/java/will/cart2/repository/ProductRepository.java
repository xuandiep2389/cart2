package will.cart2.repository;

import org.springframework.data.repository.CrudRepository;
import will.cart2.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
