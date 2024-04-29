package raiper.miu.cs489.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperId;
    @NotBlank
    @Column(nullable = false)
    private String firstName;
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotBlank
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String phone;
    @OneToOne()
    @JoinColumn(name = "orderId")
    private Order order;

}
