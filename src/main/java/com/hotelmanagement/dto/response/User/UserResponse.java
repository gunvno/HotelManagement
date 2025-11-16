package com.hotelmanagement.dto.response.User;

import com.hotelmanagement.enums.UserStatus;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    UserStatus status;
    String userType;
    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    Boolean deleted ;
    LocalDateTime deletedTime;
    String deletedBy;
}
