package com.hotelmanagement.dto.request.User;

import com.hotelmanagement.enums.UserStatus;
import com.hotelmanagement.exception.ErrorCode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest
{
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
    Boolean deleted = false;
    LocalDateTime deletedTime;
    String deletedBy;
}

