package raiper.miu.cs489.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(nullable = false)
    @NotBlank
    private String productName;
    @Column(nullable = false)
    @NotBlank
    private String productDescription;
    @Column(nullable = false)
    @NotNull
    private Double productPrice;
    @Column(nullable = false)
    @NotNull
    private Integer productQuantity;
    @Column(nullable = false)
    @NotBlank
    private String productImageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "supplierId", unique = false, nullable = false)
    private Supplier supplier;

}
