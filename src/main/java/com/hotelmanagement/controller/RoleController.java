package com.hotelmanagement.controller;

import com.hotelmanagement.dto.request.Role.AddPermissionToRoleRequest;
import com.hotelmanagement.dto.response.ApiResponse;
import com.hotelmanagement.service.interfaces.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/roles")
@RestController
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    IRoleService IRoleService;
    @PostMapping("addPermissions")
    public ApiResponse<String> addPermissionsToRoles(@RequestBody @Valid AddPermissionToRoleRequest request) {
        return ApiResponse.<String>builder()
                .success("true")
                .code(1000)
                .result(IRoleService.addPermissionToRole(request))
                .message(IRoleService.addPermissionToRole(request))
                .build();
    }
}
