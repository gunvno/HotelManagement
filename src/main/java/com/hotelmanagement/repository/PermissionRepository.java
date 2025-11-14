package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, String> {
    Permissions findByName(String name);
}
