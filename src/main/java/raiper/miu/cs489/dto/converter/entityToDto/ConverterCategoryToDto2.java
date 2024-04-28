package raiper.miu.cs489.dto.converter.entityToDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.dto.response.CategoryResponse2;
import raiper.miu.cs489.model.Category;


@Component
public class ConverterCategoryToDto2 implements Converter<Category, CategoryResponse2> {

    @Override
    public CategoryResponse2 convert(Category source) {

        var categoryResponse = new CategoryResponse2(source.getCategoryId(),
                source.getCategoryName(),
                source.getDescription());
        return categoryResponse;
    }
}
