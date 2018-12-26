package will.cart2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import will.cart2.model.Item;
import will.cart2.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    public ProductService productService;

    @GetMapping("")
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("cart/index");
        return modelAndView;
    }

    @GetMapping("/buy/{id}")
    public ModelAndView buy(@PathVariable("id") int id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cart");
        if (session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productService.findById(id), 1));
            session.setAttribute("cart", cart);
        } else {

        }
        return modelAndView;
    }
}
