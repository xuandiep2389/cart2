package will.cart2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import will.cart2.model.Order;
import will.cart2.service.OrderService;

import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @GetMapping("/create")
    public ModelAndView showCreateOrderPage() {
        ModelAndView modelAndView = new ModelAndView("order/create");
        modelAndView.addObject("order",new Order());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNewOrder(@ModelAttribute Order order) {
        ModelAndView modelAndView = new ModelAndView("order/create");
        Date date = new Date();
        String orderDate = date.toString();
        order.setOrderDate(orderDate);
        orderService.save(order);
        modelAndView.addObject("order",new Order());
        modelAndView.addObject("message","Update your information success");
        return modelAndView;
    }

}
