package raiper.miu.cs489.dto.response;

public record ProductResponse2(Integer productId,
                               String productName,
                               String productDescription,
                               Double productPrice,
                               Integer productQuantity,
                               String productImageUrl,
                               CategoryResponse2 category) {
}
