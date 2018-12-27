package will.cart2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import will.cart2.model.Item;
import will.cart2.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    public ProductService productService;

    @GetMapping("")
    public ModelAndView showIndex(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("cart/index");
        modelAndView.addObject("totalMoney", totalMoney(session));
        return modelAndView;
    }

    @GetMapping("/buy/{id}")
    public ModelAndView buy(@PathVariable("id") int id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cart");
        List<Item> cart;
        if (session.getAttribute("cart") == null){
            cart = new ArrayList<Item>();
            cart.add(new Item(productService.findById(id), 1));
            session.setAttribute("cart", cart);
        } else {
            cart = (List<Item>) session.getAttribute("cart");
            int index = isExists(id, cart);
            if (index == -1) {
                cart.add(new Item(productService.findById(id), 1));
            }else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return modelAndView;
    }


    @GetMapping("/remove/{id}")
    public ModelAndView removeItem(@PathVariable("id") int id, HttpSession session) {
        List<Item> cart;
        cart = (List<Item>) session.getAttribute("cart");
        int index = isExists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        ModelAndView modelAndView = new ModelAndView("redirect:/cart");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCart(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cart");
        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart;
        cart = (List<Item>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(HttpSession session) {
       if (session.getAttribute("username") == null) {
            ModelAndView modelAndView = new ModelAndView("redirect:/account");
            return modelAndView;
       } else {
           ModelAndView modelAndView = new ModelAndView("orders/thanks");
           return modelAndView;
       }
    }


    private int isExists(int id, List<Item> cart) {
        for (int i = 0; i < cart.size() ; i++) {
            if (cart.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private double totalMoney(HttpSession session) {
        double totalMoney = 0;
        List<Item> cart;
        cart = (List<Item>) session.getAttribute("cart");
        for (Item item : cart) {
            totalMoney += item.getQuantity() * item.getProduct().getPrice();
        }
        return totalMoney;
    }

}
