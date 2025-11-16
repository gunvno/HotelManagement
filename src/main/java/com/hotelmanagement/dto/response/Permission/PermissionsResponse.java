package com.hotelmanagement.dto.response.Permission;

import com.hotelmanagement.entity.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionsResponse {
    String name;
    String description;
    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    LocalDateTime deletedTime;
    String deletedBy;
    Set<Roles> roles;
}
