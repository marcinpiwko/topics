package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByType(Role.RoleType type);

    Boolean existsByType(Role.RoleType type);
}
