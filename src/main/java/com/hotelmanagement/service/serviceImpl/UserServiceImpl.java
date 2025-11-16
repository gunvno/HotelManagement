package com.hotelmanagement.service.serviceImpl;

import com.hotelmanagement.dto.request.User.UserDeleteRequest;
import com.hotelmanagement.dto.request.User.UserGetByIdRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.User.UserResponse;
import com.hotelmanagement.entity.Accounts;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.enums.UserStatus;
import com.hotelmanagement.exception.AppException;
import com.hotelmanagement.exception.ErrorCode;
import com.hotelmanagement.mapper.UserMapper;
import com.hotelmanagement.repository.AccountRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.repository.UserRepository;
import com.hotelmanagement.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
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

    @PreAuthorize("hasAuthority('GET_ALL_USER')")
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream()
                .filter(user -> !user.getDeleted())
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
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
        if(user.getDeleted())
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        user.setDeleted(true);
        userMapper.toUserResponse(userRepository.save(user));
        return "xoa thanh cong";
    }
    @PreAuthorize("hasRole('ADMIN')")
    public String unActiveUser(UserDeleteRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        if(user.getStatus().equals(UserStatus.UNACTIVE))
            throw new AppException(ErrorCode.USER_ALREADY_UNACTIVE);
        user.setStatus(UserStatus.UNACTIVE);
        userMapper.toUserResponse(userRepository.save(user));
        return "UnActive thanh cong";
    }
    @PreAuthorize("hasRole('ADMIN')")
    public String activeUser(UserDeleteRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        if(user.getStatus().equals(UserStatus.ACTIVE))
            throw new AppException(ErrorCode.USER_ALREADY_ACTIVE);
        user.setStatus(UserStatus.ACTIVE);
        userMapper.toUserResponse(userRepository.save(user));
        return "UnActive thanh cong";
    }
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(UserGetByIdRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Accounts accounts = accountRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        User user = userRepository.findById(accounts.getUser().getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }





}
