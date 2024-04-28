package raiper.miu.cs489.dto.response;

public record ProductResponse(Integer productId,
                             String productName,
                             String productDescription,
                             Double productPrice,
                             Integer productQuantity,
                             String productImageUrl) {
}
