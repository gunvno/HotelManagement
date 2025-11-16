package com.hotelmanagement.service.interfaces;

import com.hotelmanagement.dto.request.Role.AddPermissionToRoleRequest;

public interface IRoleService {
    String addPermissionToRole(AddPermissionToRoleRequest request);
}
