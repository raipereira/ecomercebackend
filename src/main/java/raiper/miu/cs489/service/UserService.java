package raiper.miu.cs489.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raiper.miu.cs489.model.Customer;
import raiper.miu.cs489.model.MyUserDetails;
import raiper.miu.cs489.model.Role;
import raiper.miu.cs489.repository.RoleRepository;
import raiper.miu.cs489.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

     private RoleRepository roleRepository;
     private UserRepository userRepository;
     private PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerUser(Customer customer) {

        List<Role> listAdminRoles = new ArrayList<>();
        var adminRole = roleRepository.findByRole("ROLE_CUSTOMER");
        if(adminRole.isEmpty()) {
            var newAdminRole = Role.builder().role("ROLE_CUSTOMER").build();
            listAdminRoles.add(newAdminRole);
        } else {
            listAdminRoles.add(adminRole.get());
        }
        var user = customer.getUser();
        user.setRoles(listAdminRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCustomer(customer);

        user = this.userRepository.save(user);

        return user.getCustomer();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return  userRepository.findByUsername(username).map(user -> new MyUserDetails(user))
                 .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " is not found"));

    }
}
