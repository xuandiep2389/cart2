package will.cart2.service;

import will.cart2.model.Order;

public interface OrderService {

    Iterable<Order> getAllOrders();

    Order findById(int id);

    void save(Order order);

    void delete(int id);
}
