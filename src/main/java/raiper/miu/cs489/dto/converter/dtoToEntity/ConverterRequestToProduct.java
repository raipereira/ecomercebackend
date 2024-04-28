package raiper.miu.cs489.dto.converter.dtoToEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.request.ProductRequest;
import raiper.miu.cs489.model.Product;


@Component
public class ConverterRequestToProduct implements Converter<ProductRequest, Product> {

    private ConverterRequestToCategory converterDtoToCategory;
    private ConverterRequestToSupplier converterDtoToSupplier;

    public ConverterRequestToProduct(ConverterRequestToCategory converterDtoToCategory,
                                     ConverterRequestToSupplier converterDtoToSupplier) {
        this.converterDtoToCategory = converterDtoToCategory;
        this.converterDtoToSupplier = converterDtoToSupplier;
    }

    @Override
    public Product convert(ProductRequest source) {
        var product = new Product().builder()
                .productName(source.productName())
                .productDescription(source.productDescription())
                .productQuantity(source.productQuantity())
                .productPrice(source.productPrice())
                .productImageUrl(source.productImageUrl())
                .category(converterDtoToCategory.convert(source.category()))
                .supplier(converterDtoToSupplier.convert(source.supplier()))
                .build();
        return product;
    }
}
