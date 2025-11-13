package com.hotelmanagement.service.serviceImpl;

import com.hotelmanagement.dto.request.Account.AccountCreationRequest;
import com.hotelmanagement.dto.request.Authentication.RegisterRequest;
import com.hotelmanagement.dto.request.User.UserCreationRequest;
import com.hotelmanagement.dto.request.User.UserDeleteRequest;
import com.hotelmanagement.dto.request.User.UserGetByIdRequest;
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
import java.util.Optional;
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
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        return userMapper.toUserResponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(UserDeleteRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        user.setDeleted(true);
        userMapper.toUserResponse(userRepository.save(user));
        return "xoa thanh cong";
    }
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserById(UserGetByIdRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }




}
