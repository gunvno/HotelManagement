package com.hotelmanagement.dto.response.Role;

import com.hotelmanagement.entity.Permissions;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String id;
    String name;
    String roleCode;
    Boolean status;
    Set<Permissions> permissions;
    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    LocalDateTime deletedTime;
    String deletedBy;
}
