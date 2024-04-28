package raiper.miu.cs489.service;


import raiper.miu.cs489.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product createNewProduct(Product product);
}
