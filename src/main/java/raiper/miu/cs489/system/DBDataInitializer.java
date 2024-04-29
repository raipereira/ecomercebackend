package raiper.miu.cs489.system;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raiper.miu.cs489.model.*;
import raiper.miu.cs489.repository.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DBDataInitializer {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private ShipperRepository shipperRepository;
    private PaymentRepository paymentRepository;
    private PasswordEncoder passwordEncoder;
    DBDataInitializer(CustomerRepository customerRepository, UserRepository userRepository,
                      RoleRepository roleRepository,
                      ProductRepository productRepository,
                      OrderRepository orderRepository,
                      ShipperRepository shipperRepository,
                      PaymentRepository paymentRepository,
                      PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.shipperRepository = shipperRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initAdminUser() {

        var adminUser = userRepository.findByUsername("admin");
        if(adminUser.isEmpty()) {
            List<Role> listAdminRoles = new ArrayList<>();
            var adminRole = roleRepository.findByRole("ROLE_ADMIN");
            if(adminRole.isEmpty()) {
                var newAdminRole = Role.builder().role("ROLE_ADMIN").build();
                listAdminRoles.add(newAdminRole);
            } else {
                listAdminRoles.add(adminRole.get());
            }
            var address = Address.builder().street("9050 Main St")
                    .city("San Jose").zipCode("678432")
                    .state("California").build();

            var customerAdmin = Customer.builder()
                    .firstName("Rai")
                    .lastName("Per")
                    .phoneNumber("123-456-7890")
                    .address(address)
                    .build();

            var userAdmin = User.builder()
                    .username("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("test123"))
                    .customer(customerAdmin)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .roles(listAdminRoles).build();
            userAdmin.setRoles(listAdminRoles);
            userRepository.save(userAdmin);
        }
    }


    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {

            var address1 = Address.builder()
                    .street("1000 4th")
                    .city("Fairfield").zipCode("52557")
                    .state("Iowa").build();

            var address2 = Address.builder()
                    .street("456 Elm St").city("Las Vegas")
                    .zipCode("52557").state("Nevada").build();

            var address3 = Address.builder().street("123 Main St")
                    .city("San Diego").zipCode("12345")
                    .state("California").build();

            var address4 = Address.builder().street("456 MLK St")
                    .city("Dallas").zipCode("546663")
                    .state("Texas").build();

            var customer1 = Customer.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .phoneNumber("123-456-7890")
                    .address(address1)
                    .build();

            var newCustomerRoles = Arrays.asList(Role.builder().role("ROLE_USER").build());
            var customerUser = User.builder()
                    .username("John")
                    .email("John@john.com")
                    .password(passwordEncoder.encode("test123"))
                    .customer(customer1)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .roles(newCustomerRoles).build();
            userRepository.save(customerUser);

            // fill category and product

            var category1 = Category.builder()
                    .categoryName("Electronics")
                    .description("Electronic products")
                    .build();

            var category2 = Category.builder()
                    .categoryName("Clothing")
                    .description("Clothing items")
                    .build();

            var supplier1 = Supplier.builder()
                    .supplierName("Supplier 1")
                    .supplierPhone("123-456-7890")
                    .supplierEmail("supplier1@example.com")
                    .supplierAddress(address2)
                    .build();

            var supplier2 = Supplier.builder()
                    .supplierName("Supplier 2")
                    .supplierPhone("987-654-3210")
                    .supplierEmail("supplier2@example.com")
                    .supplierAddress(address3)
                    .build();

            var supplier3 = Supplier.builder()
                    .supplierName("Supplier 3")
                    .supplierPhone("555-123-4567")
                    .supplierEmail("supplier3@example.com")
                    .supplierAddress(address4)
                    .build();


            var product1 = Product.builder()
                    .productName("Laptop")
                    .productDescription("A high-performance laptop")
                    .productPrice(999.99)
                    .productQuantity(10)
                    .productImageUrl("laptop_image_url")
                    .category(category1)
                    .supplier(supplier1)
                    .build();

//            var product = new Product("Laptop", "A high-performance laptop",
//                    100.00, 10, "laptop_image_url", category1, supplier1);

            var product2 = Product.builder()
                    .productName("T-shirt")
                    .productDescription("Comfortable cotton T-shirt")
                    .productPrice(19.99)
                    .productQuantity(50)
                    .productImageUrl("tshirt_image_url")
                    .category(category2)
                    .supplier(supplier2)
                    .build();

            var product3 = Product.builder()
                    .productName("Smartphone")
                    .productDescription("Latest smartphone model")
                    .productPrice(699.99)
                    .productQuantity(20)
                    .productImageUrl("smartphone_image_url")
                    .category(category1)
                    .supplier(supplier3)
                    .build();


            productRepository.saveAll(Arrays.asList(product1, product2, product3));

            // Fetch customer and product by id
            var customer = customerRepository.findById(2).get();
            var prod1 = productRepository.findById(1).get();
            var prod2 = productRepository.findById(2).get();

            // Create dummy data for orders
            Order order1 = Order.builder()
                    .orderDate(LocalDateTime.now())
                    .customer(customer)
                    //.orderDetails(Arrays.asList(orderDetail1))
                    .build();

            Order order2 = Order.builder()
                    .orderDate(LocalDateTime.now().plusDays(1))
                    .customer(customer)
                    .build();


            // Create dummy data for order details
            OrderDetail orderDetail1 = new OrderDetail();
            orderDetail1.setId(new OrderDetailId(order1, prod1));
            orderDetail1.setQuantity(2);
            orderDetail1.setPrice(999.99);

            OrderDetail orderDetail2 = new OrderDetail();
            orderDetail2.setId(new OrderDetailId(order2, prod2));
            orderDetail2.setQuantity(1);
            orderDetail2.setPrice(699.99);

            order1.setOrderDetails(Collections.singletonList(orderDetail1));
            order2.setOrderDetails(Collections.singletonList(orderDetail2));

            orderRepository.saveAll(Arrays.asList(order1, order2));

            var o1 = orderRepository.findById(1).get();
            var o2 = orderRepository.findById(2).get();

            Payment payment1 = Payment.builder()
                    .amount(new BigDecimal("100.00"))
                    .status(StatusPayment.COMPLETED)
                    .type(TypeOfPayment.CREDIT_CARD)
                    .paymentDate(LocalDate.now())
                    .order(o1)
                    .build();

            Payment payment2 = Payment.builder()
                    .amount(new BigDecimal("50.00"))
                    .status(StatusPayment.PENDING)
                    .type(TypeOfPayment.PAYPAL)
                    .paymentDate(LocalDate.now().plusDays(1))
                    .order(o2)
                    .build();

            paymentRepository.saveAll(Arrays.asList(payment1, payment2));



            // Create dummy data for shipper
            var shipper1 = Shipper.builder()
                    .firstName("Shipper")
                    .lastName("Smith")
                    .email("shipper@example.com")
                    .phone("987-654-3210")
                    .order(o1)
                    .build();
            var shipper2 = Shipper.builder()
                    .firstName("Shipper2")
                    .lastName("John")
                    .email("shipper@example.com")
                    .phone("987-654-3210")
                    .order(o2)
                    .build();
           shipperRepository.saveAll(Arrays.asList(shipper1,shipper2));

        };
    }
}
