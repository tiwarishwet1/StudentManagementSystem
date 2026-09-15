package in.mindcraft.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import in.mindcraft.security.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        userDetailsService
                );

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public JwtAuthenticationConverter
            jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter
                grantedAuthoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        grantedAuthoritiesConverter
                .setAuthoritiesClaimName("role");

        grantedAuthoritiesConverter
                .setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                grantedAuthoritiesConverter
        );

        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/auth/**")
                .permitAll()

                .requestMatchers(
                        HttpMethod.GET,
                        "/students/**"
                )
                .authenticated()

                .anyRequest()
                .authenticated()
            )

            .oauth2ResourceServer(oauth2 ->
                    oauth2.jwt(jwt ->
                            jwt.jwtAuthenticationConverter(
                                    jwtAuthenticationConverter()
                            )
                    )
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationProvider authenticationProvider) {

        return new ProviderManager(
                authenticationProvider
        );
    }

    @Bean
    public JwtDecoder jwtDecoder() {

        SecretKey key =
                new javax.crypto.spec.SecretKeySpec(
                        JwtService.SECRET.getBytes(
                                StandardCharsets.UTF_8
                        ),
                        "HmacSHA256"
                );

        return NimbusJwtDecoder
                .withSecretKey(key)
                .build();
    }
}