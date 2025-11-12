package com.hotelmanagement.controller;

import com.hotelmanagement.dto.request.Authentication.RegisterRequest;
import com.hotelmanagement.dto.request.User.UserCreationRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.ApiResponse;
import com.hotelmanagement.dto.response.User.UserResponse;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    ApiResponse<String> register(@RequestBody @Valid RegisterRequest request){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.register(request));
        return apiResponse;

    }


}
