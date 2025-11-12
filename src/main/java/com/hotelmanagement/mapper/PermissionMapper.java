package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.request.Permission.PermissionCreationRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.Permission.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.security.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    void updatePermission(@MappingTarget Permission permission , UserUpdateRequest request);
}
