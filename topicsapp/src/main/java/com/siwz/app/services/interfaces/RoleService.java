package com.siwz.app.services.interfaces;

import com.siwz.app.persistence.model.Role;
import com.siwz.app.utils.errors.ApplicationException;

public interface RoleService {

    Boolean checkIfRoleExists(Role.RoleType type);

    Role getRoleByType(Role.RoleType type) throws ApplicationException;
}
