package raiper.miu.cs489.dto.response;

import java.util.List;

public record CategoryResponse(
         Integer categoryId,
         String categoryName,
         String description,
         List<ProductResponse> products
) {


}
