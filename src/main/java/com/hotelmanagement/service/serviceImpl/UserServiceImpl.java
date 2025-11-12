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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser(){

        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }


}
