package raiper.miu.cs489.dto.request;

public record SupplierRequest(
         String supplierName,
         String supplierPhone,
         String supplierEmail,
         AddressRequest supplierAddress
) {
}
