package com.siwz.app.services;

import com.siwz.app.persistence.dto.Role;
import com.siwz.app.persistence.repositories.RoleRepository;
import com.siwz.app.services.interfaces.RoleService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Boolean checkIfRoleExists(Role.RoleType type) {
        return roleRepository.existsByType(type);
    }

    public Role getRoleByType(Role.RoleType type) throws ApplicationException {
        return roleRepository.findByType(type)
                .orElseThrow(() -> new ApplicationException(DAOError.DAO_ROLE_NOT_FOUND, type));
    }
}