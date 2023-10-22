package tn.mycompany.jpamanytomany.services;

import tn.mycompany.jpamanytomany.entities.Role;
import tn.mycompany.jpamanytomany.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tn.mycompany.jpamanytomany.repositories.RoleRepository;
import tn.mycompany.jpamanytomany.repositories.UserRepository;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        //hasher le mot passe puis enregistrer
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {

        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
        role.getUsers().add(user);
        //userRepository.save(user);
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = userRepository.findByUsername(userName);
        if (user == null) throw new RuntimeException("Bad Credentials");
        if (password.equals(user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Bad Credentials");
    }
}