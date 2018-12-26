package will.cart2.service;

import will.cart2.model.Product;

public interface ProductService {

    Iterable<Product> getAllProducts();

    Product findById(int id);

    void save(Product product);

    void delete(int id);
}
