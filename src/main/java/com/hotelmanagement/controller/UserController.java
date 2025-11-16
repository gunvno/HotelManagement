package com.hotelmanagement.controller;

import com.hotelmanagement.dto.request.User.UserDeleteRequest;
import com.hotelmanagement.dto.request.User.UserGetByIdRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.ApiResponse;
import com.hotelmanagement.dto.response.User.UserResponse;
import com.hotelmanagement.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private IUserService IUserService;
    @GetMapping("/getAll")
    ApiResponse<List<UserResponse>> getAll(){
          return  ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .success("true")
                .result(IUserService.getAllUser())
                .build();
    }
    @PostMapping("/update")
    ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request){
        return  ApiResponse.<UserResponse>builder()
                .code(1000)
                .success("true")
                .result(IUserService.updateUser( request))
                .build();
    }
    @PostMapping("/delete")
    ApiResponse<String> deleteUser(@RequestBody @Valid UserDeleteRequest request){
        return ApiResponse.<String>builder()
                .code(1000)
                .success("true")
                .result(IUserService.deleteUser(request))
                .build();
    }
    @PostMapping("/unActive")
    ApiResponse<String> unActiveUser(@RequestBody @Valid UserDeleteRequest request){
        return ApiResponse.<String>builder()
                .code(1000)
                .success("true")
                .result(IUserService.unActiveUser(request))
                .build();
    }
    @PostMapping("/active")
    ApiResponse<String> activeUser(@RequestBody @Valid UserDeleteRequest request){
        return ApiResponse.<String>builder()
                .code(1000)
                .success("true")
                .result(IUserService.activeUser(request))
                .build();
    }
    @GetMapping("/getById")
    ApiResponse<UserResponse> getUserById(@RequestBody @Valid UserGetByIdRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .success("true")
                .result(IUserService.getUserById(request))
                .build();
    }
    @GetMapping("/getMyInfo")
    ApiResponse<UserResponse> getMyInfo() {
        UserResponse user = IUserService.getMyInfo();
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .success("true")
                .result(user)
                .build();


    }
}
