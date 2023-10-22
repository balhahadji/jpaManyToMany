package tn.mycompany.jpamanytomany;

import tn.mycompany.jpamanytomany.entities.Role;
import tn.mycompany.jpamanytomany.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.mycompany.jpamanytomany.services.UserService;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaManyToManyApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpaManyToManyApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User user = new User();
            user.setUsername("user1");
            user.setPassword("123456");
            userService.addNewUser(user);
            User user1 = new User();
            user.setUsername("admin");
            user.setPassword("123456");
            userService.addNewUser(user);
            Stream.of("STUDENT", "USER", "ADMIN").forEach(name -> {
                Role role1 = new Role();
                role1.setRoleName(name);
                userService.addNewRole(role1);
            });
            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");
            try {
                User user2 = userService.authenticate("user1", "123456");
                System.out.println(user2.getUserId());
                System.out.println(user2.getUsername());
                System.out.println("Roles=>");
                user2.getRoles().forEach(role ->
                        System.out.println("role= " + role));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }


}
