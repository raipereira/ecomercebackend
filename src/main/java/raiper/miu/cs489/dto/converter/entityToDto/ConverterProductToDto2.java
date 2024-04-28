package raiper.miu.cs489.dto.converter.entityToDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.response.ProductResponse2;
import raiper.miu.cs489.model.Product;


@Component
public class ConverterProductToDto2 implements Converter<Product, ProductResponse2> {

    private ConverterCategoryToDto2 categoryToDto;
    public ConverterProductToDto2(ConverterCategoryToDto2 categoryToDto) {
        this.categoryToDto = categoryToDto;
    }

    @Override
    public ProductResponse2 convert(Product source) {
        var productResponse = new ProductResponse2(source.getProductId(),
                source.getProductName(),
                source.getProductDescription(),
                source.getProductPrice(),
                source.getProductQuantity(),
                source.getProductImageUrl(),
                categoryToDto.convert(source.getCategory()));
        return productResponse;
    }
}
