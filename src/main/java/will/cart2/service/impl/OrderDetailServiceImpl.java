package will.cart2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import will.cart2.model.OrderDetail;
import will.cart2.repository.OrderDetailRepository;
import will.cart2.repository.OrderRepository;
import will.cart2.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public Page<OrderDetail> getAllOrdersDetail(Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }

    @Override
    public OrderDetail findById(int id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(int id) {
        orderDetailRepository.deleteById(id);
    }
}
