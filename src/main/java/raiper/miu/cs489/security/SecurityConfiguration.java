package raiper.miu.cs489.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfiguration {

    //@Value("${api.endpoint.base-url}")
    private static final String baseUrl = "/api/v1/";

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public SecurityConfiguration() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
         this.publicKey = (RSAPublicKey) keyPair.getPublic();
         this.privateKey = (RSAPrivateKey) keyPair.getPrivate();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
                .authorizeHttpRequests(authorizationRequests -> authorizationRequests
                        .requestMatchers(GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(GET, this.baseUrl + "addresses/**").permitAll()
                        .requestMatchers(GET, this.baseUrl + "categories/**").permitAll()
                        .requestMatchers(GET, this.baseUrl + "products").permitAll()
                        .requestMatchers(GET, this.baseUrl + "users/register").permitAll()
                        .requestMatchers(POST, this.baseUrl + "users/register").permitAll()
                        .requestMatchers(POST, this.baseUrl + "users/login").permitAll()
                        .requestMatchers(GET, this.baseUrl + "orders/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(POST, this.baseUrl + "products").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(POST, this.baseUrl + "categories").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(POST, this.baseUrl + "orders").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(_headers -> _headers.frameOptions(frameOption -> frameOption.disable())) //This is for h2 browser console access
                .csrf(_csrf -> _csrf.disable())
                .httpBasic(Customizer.withDefaults())
               .oauth2ResourceServer(oath2ResourceServer -> oath2ResourceServer.jwt(jwt ->jwt.jwtAuthenticationConverter(
                       jwtAuthenticationConverter()
               )))
               .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext>  jwtSet = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwtSet);
    }


    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}
