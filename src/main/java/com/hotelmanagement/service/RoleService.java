package com.hotelmanagement.service;

import com.hotelmanagement.dto.request.Role.RoleCreationRequest;
import com.hotelmanagement.dto.response.Role.RoleResponse;

import java.util.List;

public interface RoleService {
    public RoleResponse createRole(RoleCreationRequest request);
    public List<RoleResponse> getAll();
}
