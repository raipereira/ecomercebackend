package raiper.miu.cs489.dto.request;

public record OrderRequest(String userEmail, Integer productId, Integer quantity,
                           double productPrice, String paymentType) {
}
