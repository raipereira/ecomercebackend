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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressId")
    private Address address;
    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private User user;
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

    public String getEmail() {
        return user != null ? user.getEmail() : null;
    }
}
