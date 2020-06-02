package com.siwz.app.persistence.repositories;

import com.siwz.app.persistence.dto.Role;
import com.siwz.app.persistence.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByIndexNo(String indexNo);

    List<User> findByRole(Role role);
}
