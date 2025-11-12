package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.request.Role.RoleCreationRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.Role.RoleResponse;
import com.hotelmanagement.entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.security.Permission;
@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Roles toRole(RoleCreationRequest request);
    RoleResponse toRoleResponse(Roles role);
    void updatePermission(@MappingTarget Permission permission , UserUpdateRequest request);
}
