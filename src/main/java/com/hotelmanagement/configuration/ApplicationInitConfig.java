package com.hotelmanagement.configuration;

import com.hotelmanagement.entity.Accounts;
import com.hotelmanagement.entity.Permissions;
import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.enums.AccountStatus;
import com.hotelmanagement.enums.Role;
import com.hotelmanagement.enums.UserStatus;
import com.hotelmanagement.mapper.PermissionMapper;
import com.hotelmanagement.repository.AccountRepository;
import com.hotelmanagement.repository.PermissionRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class ApplicationInitConfig {


    @Value("#{'${admin-permission}'.split(',')}")
    private List<String> adminPermissions;

    @Value("#{'${staff-permission}'.split(',')}")
    private List<String> staffPermissions;

    @Value("#{'${customer-permission}'.split(',')}")
    private List<String> customerPermissions;

    private Permissions getOrCreatePermission(String permissionName,
                                              PermissionRepository permissionRepository) {

        Permissions permission = permissionRepository.findByName(permissionName.trim());

        if (permission == null) {
            // Chưa có → Tạo mới
            permission = Permissions.builder()
                    .name(permissionName.trim())
                    .description("Auto-generated: " + permissionName.trim())
                    .createdTime(LocalDateTime.now())
                    .deleted(false)
                    .build();
            permissionRepository.save(permission);
        }

        return permission;
    }
    private Set<Permissions> convertPermissionNamesToSet(List<String> permissionNames,
                                                         PermissionRepository permissionRepository) {
        Set<Permissions> permissions = new HashSet<>();

        for (String permissionName : permissionNames) {
            Permissions permission = getOrCreatePermission(permissionName, permissionRepository);
            permissions.add(permission);
        }

        return permissions;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public ApplicationRunner applicationRunner(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
                                               UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {
//            Roles customerRole = roleRepository.findByName(Role.CUSTOMER);
//            Roles staffRole = roleRepository.findByName(Role.STAFF);
//            Roles adminRole = roleRepository.findByName(Role.ADMIN);
//            if(customerRole == null){
//                Roles roles = Roles.builder()
//                        .name(Role.CUSTOMER)
//                        .createdTime(LocalDateTime.now())
//                        .createdBy(null)
//                        .modifiedTime(null)
//                        .modifiedBy(null)
//                        .deleted(false)
//                        .deletedTime(null)
//                        .deletedBy(null)
//                        .build();
//                roleRepository.save(roles);
//                Set<Permissions> customerPermissionSet = convertPermissionNamesToSet(customerPermissions, permissionRepository);
//                roles.setPermissions(customerPermissionSet);
//                roleRepository.save(roles);
//                log.info("✅ CUSTOMER role initialized with permissions: {}", customerPermissions);
//            }

//            if(staffRole == null){
//                Roles roles = Roles.builder()
//                        .name(Role.STAFF)
//                        .createdTime(LocalDateTime.now())
//                        .createdBy(null)
//                        .modifiedTime(null)
//                        .modifiedBy(null)
//                        .deleted(false)
//                        .deletedTime(null)
//                        .deletedBy(null)
//                        .build();
//                roleRepository.save(roles);
//                Set<Permissions> staffPermissionSet = convertPermissionNamesToSet(staffPermissions, permissionRepository);
//                roles.setPermissions(staffPermissionSet);
//                roleRepository.save(roles);
//                log.info("✅ STAFF role initialized with permissions: {}", staffPermissions);
//            }

            if (accountRepository.findByUsername("customer").isEmpty()) {
                User user = User.builder()
                        .userType("CUSTOMER")
                        .firstName("customer")
                        .lastName("customer")
                        .status(UserStatus.ACTIVE)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();
                userRepository.save(user);

                // 3️⃣ Gán role admin
                Set<Roles> customerRole = new HashSet<>();
                Roles roles = Roles.builder()
                        .name(Role.CUSTOMER)
                        .createdTime(LocalDateTime.now())
                        .createdBy(null)
                        .modifiedTime(null)
                        .modifiedBy(null)
                        .deleted(false)
                        .deletedTime(null)
                        .deletedBy(null)
                        .build();
                customerRole.add(roles);

                Accounts customerAccount = Accounts.builder()
                        .username("customer")
                        .password(passwordEncoder.encode("customer"))
                        .roles(customerRole)
                        .userId(user.getId())
                        .status(AccountStatus.ACTIVE)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();

                accountRepository.save(customerAccount); // ✅ Hibernate sẽ insert account_roles ở đây
                log.warn("✅ Customer account created with default password 'customer'.");
                Set<Permissions> customerPermissionSet = convertPermissionNamesToSet(customerPermissions, permissionRepository);
                roles.setPermissions(customerPermissionSet);
                roleRepository.save(roles);
                log.info("✅ CUSTOMER role initialized with permissions: {}", customerPermissions);
            }
            if (accountRepository.findByUsername("staff").isEmpty()) {
                User user = User.builder()
                        .userType("STAFF")
                        .firstName("staff")
                        .lastName("staff")
                        .status(UserStatus.ACTIVE)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();
                userRepository.save(user);

                // 3️⃣ Gán role admin
                Set<Roles> staffRole = new HashSet<>();
                Roles roles = Roles.builder()
                        .name(Role.STAFF).createdTime(LocalDateTime.now())
                        .createdBy(null)
                        .modifiedTime(null)
                        .modifiedBy(null)
                        .deleted(false)
                        .deletedTime(null)
                        .deletedBy(null)
                        .build();
                staffRole.add(roles);

                Accounts staffAccount = Accounts.builder()
                        .username("staff")
                        .password(passwordEncoder.encode("staff"))
                        .roles(staffRole)
                        .userId(user.getId())
                        .status(AccountStatus.ACTIVE)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();

                accountRepository.save(staffAccount); // ✅ Hibernate sẽ insert account_roles ở đây
                log.warn("✅ Admin account created with default password 'admin'.");
                Set<Permissions> staffPermissionSet = convertPermissionNamesToSet(staffPermissions, permissionRepository);
                roles.setPermissions(staffPermissionSet);
                roleRepository.save(roles);
                log.info("✅ STAFF role initialized with permissions: {}", staffPermissions);
            }
            if (accountRepository.findByUsername("admin").isEmpty()) {
                User user = User.builder()
                        .userType("ADMIN")
                        .firstName("System")
                        .lastName("Administrator")
                        .status(UserStatus.ACTIVE)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();
                userRepository.save(user);

                // 3️⃣ Gán role admin
                Set<Roles> adminRoles = new HashSet<>();
                Roles roles = Roles.builder()
                        .name(Role.ADMIN).createdTime(LocalDateTime.now())
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
                        .status(AccountStatus.ACTIVE)
                        .createdTime(LocalDateTime.now())
                        .deleted(false)
                        .build();

                accountRepository.save(adminAccount); // ✅ Hibernate sẽ insert account_roles ở đây
                log.warn("✅ Admin account created with default password 'admin'.");
                Set<Permissions> adminPermissionSet = convertPermissionNamesToSet(adminPermissions, permissionRepository);
                roles.setPermissions(adminPermissionSet);
                roleRepository.save(roles);
                log.info("✅ ADMIN role initialized with permissions: {}", adminPermissions);
            }


        };
    }
}

