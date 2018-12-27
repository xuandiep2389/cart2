package will.cart2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import will.cart2.model.Item;
import will.cart2.model.Order;
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

}
