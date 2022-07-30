package com.project.Hms.Repository;


import com.project.Hms.Entity.Role;
import com.project.Hms.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional <Role> findByName(RoleName roleName);


}
