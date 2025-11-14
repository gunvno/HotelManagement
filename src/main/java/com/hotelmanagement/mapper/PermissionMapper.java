package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.response.Permission.PermissionsResponse;
import com.hotelmanagement.entity.Permissions;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionsResponse toPermissionResponse(Permissions permissions);

    List<PermissionsResponse> toPermissionResponseList(List<Permissions> permissions);
}

