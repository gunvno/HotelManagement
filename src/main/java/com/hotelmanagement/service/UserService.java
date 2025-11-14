package com.hotelmanagement.service;

import com.hotelmanagement.dto.request.Authentication.RegisterRequest;
import com.hotelmanagement.dto.request.User.UserCreationRequest;
import com.hotelmanagement.dto.request.User.UserDeleteRequest;
import com.hotelmanagement.dto.request.User.UserGetByIdRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.User.UserResponse;
import com.hotelmanagement.entity.User;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllUser();
    public UserResponse updateUser(UserUpdateRequest request);
    public String deleteUser(UserDeleteRequest request);
    public UserResponse getUserById(UserGetByIdRequest request);
    public UserResponse getMyInfo();
    public String unActiveUser(UserDeleteRequest request);
    public String activeUser(UserDeleteRequest request);
}
