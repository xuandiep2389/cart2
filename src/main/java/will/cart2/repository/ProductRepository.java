package will.cart2.repository;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import will.cart2.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
