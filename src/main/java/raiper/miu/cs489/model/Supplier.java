package raiper.miu.cs489.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;
    private String supplierName;
    private String supplierPhone;
    private String supplierEmail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressId")
    private Address supplierAddress;
    @OneToMany(mappedBy = "supplier")
    private List<Product> product;

}
