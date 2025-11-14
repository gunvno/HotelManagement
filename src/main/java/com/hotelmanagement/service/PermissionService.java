package com.hotelmanagement.service;

import com.hotelmanagement.dto.request.Permission.PermissionCreationRequest;
import com.hotelmanagement.dto.response.Permission.PermissionsResponse;

import java.util.List;

public interface PermissionService {
    public String createPermission(PermissionCreationRequest request);
    public List<PermissionsResponse> getAll();
}
