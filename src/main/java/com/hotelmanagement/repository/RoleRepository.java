package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
    Roles findByName(Role name);
}
