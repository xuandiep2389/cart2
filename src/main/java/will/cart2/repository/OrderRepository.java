package will.cart2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import will.cart2.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
