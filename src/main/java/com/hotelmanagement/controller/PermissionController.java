package com.hotelmanagement.controller;

import com.hotelmanagement.dto.request.Permission.PermissionCreationRequest;
import com.hotelmanagement.dto.response.ApiResponse;
import com.hotelmanagement.service.interfaces.IPermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/permissions")
@RestController
@RequiredArgsConstructor
public class PermissionController {
    @Autowired
    private IPermissionService IPermissionService;
    @PostMapping()
    ApiResponse<String> create(@RequestBody @Valid PermissionCreationRequest request){
        return  ApiResponse.<String>builder()
                .code(1000)
                .success("true")
                .result(IPermissionService.createPermission(request))
                .build();
    }

}
