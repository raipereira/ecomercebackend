package raiper.miu.cs489.controller;

import org.springframework.web.bind.annotation.*;
import raiper.miu.cs489.dto.converter.dtoToEntity.ConverterRequestToProduct;
import raiper.miu.cs489.dto.converter.entityToDto.ConverterProductToDto2;
import raiper.miu.cs489.dto.request.ProductRequest;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;
import raiper.miu.cs489.service.ProductService;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService service;

    private ConverterProductToDto2 productToDto;
    private ConverterRequestToProduct converterRequestToProduct;

    public ProductController(ProductService service, ConverterProductToDto2 productToDto,
                             ConverterRequestToProduct converterRequestToProduct) {
        this.service = service;
        this.productToDto = productToDto;
        this.converterRequestToProduct = converterRequestToProduct;
    }

    @GetMapping
    public Result getAllProducts() {
        var products = service.findAllProducts();
        var productDto = products.stream().map(product -> productToDto.convert(product)).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", productDto);
    }

    @PostMapping
    public Result addNewProduct(@RequestBody ProductRequest request) {
        var product = service.createNewProduct(converterRequestToProduct.convert(request));
        var productResponse = productToDto.convert(product);
        return new Result(true, StatusCode.SUCCESS, "Add Success", productResponse);
    }
}
