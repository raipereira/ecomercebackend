package raiper.miu.cs489.service.impl;

import org.springframework.stereotype.Service;
import raiper.miu.cs489.model.Product;
import raiper.miu.cs489.repository.CategoryRepository;
import raiper.miu.cs489.repository.ProductRepository;
import raiper.miu.cs489.repository.SupplierRepository;
import raiper.miu.cs489.service.ProductService;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createNewProduct(Product product) {
        var category = categoryRepository.findByCategoryName(product.getProductName());
        if(category.isPresent()) {
            product.setCategory(category.get());
        }
        var supplier = supplierRepository.findBySupplierEmail(product.getSupplier().getSupplierEmail());
        if(supplier.isPresent()) {
            product.setSupplier(supplier.get());
        }
        return  productRepository.save(product);
    }
}
