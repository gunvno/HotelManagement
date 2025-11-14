package com.hotelmanagement.dto.request.Authentication;

import com.hotelmanagement.entity.Roles;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @NotNull(message = "USERNAME_NOT_NULL")
    String username;
    @NotNull(message = "PASSWORD_NOT_NULL")
    String password;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;

}
