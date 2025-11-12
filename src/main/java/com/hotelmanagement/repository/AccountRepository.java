package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Accounts;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,String> {
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<Accounts> findByUsername(String username);
    boolean existsByUsername(String username);
}
