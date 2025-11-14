package com.hotelmanagement.configuration;

import com.hotelmanagement.entity.Accounts;
import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.enums.Role;
import com.hotelmanagement.repository.AccountRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public ApplicationRunner applicationRunner(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
                                               UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            Roles customerRole = roleRepository.findByName("CUSTOMER");

            if(customerRole == null){
                Roles roles = Roles.builder()
                        .name("CUSTOMER")
                        .roleCode("abc")
                        .status(true)
                        .createdTime(LocalDateTime.now())
                        .createdBy(null)
                        .modifiedTime(null)
                        .modifiedBy(null)
                        .deleted(false)
                        .deletedTime(null)
                        .deletedBy(null)
                        .build();
                roleRepository.save(roles);
            }


            if (accountRepository.findByUsername("admin").isEmpty()) {
                User user = User.builder()
                        .userType("ADMIN")
                        .firstName("System")
                        .lastName("Administrator")
                        .status(true)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();
                userRepository.save(user);

                // 3️⃣ Gán role admin
                Set<Roles> adminRoles = new HashSet<>();
                Roles roles = Roles.builder()
                        .name("ADMIN")
                        .roleCode("def")
                        .status(true)
                        .createdTime(LocalDateTime.now())
                        .createdBy(null)
                        .modifiedTime(null)
                        .modifiedBy(null)
                        .deleted(false)
                        .deletedTime(null)
                        .deletedBy(null)
                        .build();
                adminRoles.add(roles);

                Accounts adminAccount = Accounts.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(adminRoles)
                        .userId(user.getId())
                        .status(true)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();

                accountRepository.save(adminAccount); // ✅ Hibernate sẽ insert account_roles ở đây
                log.warn("✅ Admin account created with default password 'admin'.");
            }
        };
    }
}

