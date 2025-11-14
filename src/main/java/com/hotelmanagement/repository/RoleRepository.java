package com.hotelmanagement.repository;

import com.hotelmanagement.dto.response.Role.RoleResponse;
import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.enums.Role;
import com.hotelmanagement.exception.AppException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
    Roles findByName(String name);
}
