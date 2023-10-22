package tn.mycompany.jpamanytomany.repositories;

import tn.mycompany.jpamanytomany.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

}
