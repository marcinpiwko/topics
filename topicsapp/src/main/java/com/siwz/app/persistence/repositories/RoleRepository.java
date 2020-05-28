package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByType(Role.RoleType type);
}
