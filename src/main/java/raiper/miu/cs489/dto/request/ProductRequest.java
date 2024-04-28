package raiper.miu.cs489.dto.request;

public record ProductRequest(String productName,
                             String productDescription,
                             Double productPrice,
                             Integer productQuantity,
                             String productImageUrl,
                             CategoryRequest category,
                             SupplierRequest supplier) {
}
