package com.hotelmanagement.service.serviceImpl;

import com.hotelmanagement.dto.request.Role.AddPermissionToRoleRequest;
import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.enums.Role;
import com.hotelmanagement.repository.PermissionRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository rolesRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @PreAuthorize("hasRole('ADMIN')")
    public String addPermissionToRole(AddPermissionToRoleRequest request) {
        Roles role = rolesRepository.findByName(Role.valueOf(request.getRoleName()));
        if(role != null){
            var permission = permissionRepository.findByName(request.getPermissionName());
            if(permission != null){
                role.getPermissions().add(permission);
                rolesRepository.save(role);
                return "them quyen thanh cong";
            }
            else return "khong tim thay quyen";
        }
        else return "khong tim thay role";

    }
}
