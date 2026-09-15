package in.mindcraft.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.mindcraft.entity.AppUser;
import in.mindcraft.repository.AppUserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeUsers(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (appUserRepository
                    .findByUsername("admin")
                    .isEmpty()) {

                AppUser admin = new AppUser();

                admin.setUsername("admin");

                admin.setPassword(
                        passwordEncoder.encode("admin123")
                );

                admin.setRole("ADMIN");

                appUserRepository.save(admin);
            }
        };
    }
}	