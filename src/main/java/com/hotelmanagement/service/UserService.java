package com.hotelmanagement.service;

import com.hotelmanagement.dto.request.Authentication.RegisterRequest;
import com.hotelmanagement.dto.request.User.UserCreationRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.User.UserResponse;
import com.hotelmanagement.entity.User;

import java.util.List;

public interface UserService {
    public String register(RegisterRequest request);
}
