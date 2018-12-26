package will.cart2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import will.cart2.model.Product;
import will.cart2.service.ProductService;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping
    public ModelAndView showIndexPage(){
        Iterable<Product> products =  productService.getAllProducts();
        ModelAndView modelAndView = new ModelAndView("/product/index");
        modelAndView.addObject("products",products);
        return modelAndView;
    }
}
