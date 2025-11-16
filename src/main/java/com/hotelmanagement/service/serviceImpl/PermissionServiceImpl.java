package com.hotelmanagement.service.serviceImpl;

import com.hotelmanagement.dto.request.Permission.PermissionCreationRequest;
import com.hotelmanagement.dto.response.Permission.PermissionsResponse;
import com.hotelmanagement.entity.Permissions;
import com.hotelmanagement.exception.AppException;
import com.hotelmanagement.exception.ErrorCode;
import com.hotelmanagement.mapper.PermissionMapper;
import com.hotelmanagement.repository.PermissionRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.service.interfaces.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionMapper permissionMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public String createPermission(PermissionCreationRequest request) {
        var permission = permissionRepository.findByName(request.getName());
        if(permission != null)
            throw new AppException(ErrorCode.PERMISSION_IS_EXISTED);
        Permissions permissions = Permissions.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdTime(LocalDateTime.now())
                .createdBy(null)
                .modifiedTime(null)
                .modifiedBy(null)
                .deleted(false)
                .deletedTime(null)
                .deletedBy(null)
                .build();
        permissionRepository.save(permissions);
        return "Them permission thanh cong";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<PermissionsResponse> getAll() {
        return permissionRepository.findAll().stream()
                .filter(permissions -> !permissions.getDeleted())
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

}
