package tn.mycompany.jpamanytomany.repositories;

import tn.mycompany.jpamanytomany.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
