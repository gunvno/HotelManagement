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
            if(roleRepository.findByName(Role.CUSTOMER).getPermissions().isEmpty()){
                Roles roles = Roles.builder()
                        .name(Role.CUSTOMER)
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
            if(roleRepository.findByName(Role.ADMIN).getPermissions().isEmpty()){
                Roles roles = Roles.builder()
                        .name(Role.ADMIN)
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
                roleRepository.save(roles);
            }

            if (accountRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .userType("Admin")
                        .build();
                userRepository.save(user);
                Accounts accounts = Accounts.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .userId(user.getId())
                        .status(true)
                        //.roles(roles)
                        .build();
                accountRepository.save(accounts);
                log.warn("admin user has been created with default password : admin, please change it");
            }
        };
    }
}

