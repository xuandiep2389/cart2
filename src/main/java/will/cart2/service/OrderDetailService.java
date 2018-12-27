package will.cart2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import will.cart2.model.OrderDetail;

public interface OrderDetailService {

    Page<OrderDetail> getAllOrdersDetail(Pageable pageable);

    OrderDetail findById(int id);

    void save(OrderDetail orderDetail);

    void delete(int id);
}
