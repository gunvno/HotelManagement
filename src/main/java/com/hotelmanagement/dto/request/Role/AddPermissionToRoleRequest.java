package com.hotelmanagement.dto.request.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPermissionToRoleRequest {
    String roleName;
    String permissionName;
}
