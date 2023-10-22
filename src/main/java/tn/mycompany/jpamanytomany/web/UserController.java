package tn.mycompany.jpamanytomany.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.mycompany.jpamanytomany.entities.User;
import tn.mycompany.jpamanytomany.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}")
    public User getuser(@PathVariable String username) {
        return userService.findUserByUserName(username);
    }
}
