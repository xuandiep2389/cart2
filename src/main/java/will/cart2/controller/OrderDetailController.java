package will.cart2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import will.cart2.model.Item;
import will.cart2.model.Order;
import will.cart2.model.OrderDetail;
import will.cart2.service.OrderDetailService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("order-detail")
public class OrderDetailController {

    @Autowired
    public OrderDetailService orderDetailService;

    @GetMapping("/view")
    public ModelAndView showOrderDetails(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("order-detail/view");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        session.setAttribute("cart", cart);
        Order order1 = (Order) session.getAttribute("order1");
        session.setAttribute("order1",order1);

        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveOrderDetails(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:/order-detail/thanks");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        Order order1 = (Order) session.getAttribute("order1");
        double total_money = (double) session.getAttribute("total_money");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order1);
        orderDetail.setAmount(total_money);
//        for (Item item : cart){
//            orderDetail.setProduct(item.getProduct());
//        }
        orderDetailService.save(orderDetail);
        return modelAndView;
    }

    @GetMapping("/thanks")
    public ModelAndView thanks(){
        ModelAndView modelAndView = new ModelAndView("order-detail/thanks");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView showListOrderDetails (Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("order-detail/list");
        Page<OrderDetail> orderDetails = orderDetailService.getAllOrdersDetail(pageable);
        modelAndView.addObject("orderDetails",orderDetails);
        return modelAndView;
    }

}
