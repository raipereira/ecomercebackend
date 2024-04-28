package raiper.miu.cs489.dto.converter.entityToDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.response.CategoryResponse;
import raiper.miu.cs489.model.Category;


@Component
public class ConverterCategoryToDto implements Converter<Category, CategoryResponse> {

    private ConverterProductToDto converterProductToDto;
    public ConverterCategoryToDto(ConverterProductToDto converterProductToDto) {
        this.converterProductToDto = converterProductToDto;
    }

    @Override
    public CategoryResponse convert(Category source) {
        var products = source.getProducts().stream().map(converterProductToDto::convert).toList();
        var categoryResponse = new CategoryResponse(source.getCategoryId(),
                source.getCategoryName(),
                source.getDescription(),
                products);
        return categoryResponse;
    }
}
