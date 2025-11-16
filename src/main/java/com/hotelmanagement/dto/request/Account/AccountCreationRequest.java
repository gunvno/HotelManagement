package com.hotelmanagement.dto.request.Account;

import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.enums.AccountStatus;
import com.hotelmanagement.exception.ErrorCode;
import jakarta.persistence.*;
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
public class AccountCreationRequest
{
    String username;
    String password;
    AccountStatus status;
    String userId;
    Set<Roles> roles;
    LocalDateTime createdTime;
    String createdBy;
    LocalDateTime modifiedTime;
    String modifiedBy;
    Boolean deleted = false;
    LocalDateTime deletedTime;
    String deletedBy;
}

