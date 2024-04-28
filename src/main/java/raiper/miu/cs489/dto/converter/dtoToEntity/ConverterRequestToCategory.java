package raiper.miu.cs489.dto.converter.dtoToEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.request.CategoryRequest;
import raiper.miu.cs489.model.Category;


@Component
public class ConverterRequestToCategory implements Converter<CategoryRequest, Category> {

    @Override
    public Category convert(CategoryRequest source) {
        var category = new Category().builder()
                .categoryName(source.categoryName())
                .description(source.description())
                .build();
        return category;
    }


}
