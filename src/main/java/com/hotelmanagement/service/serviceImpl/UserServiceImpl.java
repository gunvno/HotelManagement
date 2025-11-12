package com.hotelmanagement.service.serviceImpl;

import com.hotelmanagement.dto.request.Account.AccountCreationRequest;
import com.hotelmanagement.dto.request.Authentication.RegisterRequest;
import com.hotelmanagement.dto.request.User.UserCreationRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.User.UserResponse;
import com.hotelmanagement.entity.Accounts;
import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.enums.Role;
import com.hotelmanagement.exception.AppException;
import com.hotelmanagement.exception.ErrorCode;
import com.hotelmanagement.mapper.UserMapper;
import com.hotelmanagement.repository.AccountRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.repository.UserRepository;
import com.hotelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public String register(RegisterRequest request){
        if(accountRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_IS_EXISTED);

        User user = User.builder()
                .userType("CUSTOMER")
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(true)
                .createdTime(LocalDateTime.now())
                .createdBy(null)
                .modifiedTime(null)
                .modifiedBy(null)
                .deleted(false)
                .deletedTime(null)
                .deletedBy(null)
                .build();
        userRepository.save(user);
        Roles customerRole = roleRepository.findByName(Role.CUSTOMER);
        Set<Roles> roles = new HashSet<>();
        roles.add(customerRole);
        Accounts accounts = Accounts.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(true)
                .userId(user.getId())
                .roles(roles)
                .createdTime(LocalDateTime.now())
                .createdBy(null)
                .modifiedTime(null)
                .modifiedBy(null)
                .deleted(false)
                .deletedTime(null)
                .deletedBy(null)
                .build();

        accountRepository.save(accounts);
        return "Dang ki thanh cong";
    }

}
