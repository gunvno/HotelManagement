package com.hotelmanagement.controller;

import com.hotelmanagement.dto.request.Authentication.*;
import com.nimbusds.jose.JOSEException;
import com.hotelmanagement.dto.response.ApiResponse;
import com.hotelmanagement.dto.response.Authentication.AuthenticationResponse;
import com.hotelmanagement.dto.response.Authentication.IntrospectResponse;
import com.hotelmanagement.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("register")
    ApiResponse<String> register(@RequestBody @Valid RegisterRequest request){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult(authenticationService.register(request));
        return apiResponse;

    }
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping(value = "/introspect", consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request){
        try {
            var result = authenticationService.introspect(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .result(result)
                    .build();
        } catch (Exception e) {
            // Xử lý lỗi (ghi log, trả response lỗi, v.v.)
            return ApiResponse.<IntrospectResponse>builder()
                    .build();
        }
    }
    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {

        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .success("true")
                .build();

    }
    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshTokenRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

}
