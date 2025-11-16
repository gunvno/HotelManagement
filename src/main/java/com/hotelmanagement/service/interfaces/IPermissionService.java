package com.hotelmanagement.service.interfaces;

import com.hotelmanagement.dto.request.Permission.PermissionCreationRequest;
import com.hotelmanagement.dto.response.Permission.PermissionsResponse;

import java.util.List;

public interface IPermissionService {
    public String createPermission(PermissionCreationRequest request);
    public List<PermissionsResponse> getAll();
}
