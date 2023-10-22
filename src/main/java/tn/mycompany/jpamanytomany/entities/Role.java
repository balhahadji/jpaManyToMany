package tn.mycompany.jpamanytomany.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DESCRIPTION") // desc est un mot clé dans sql qui peut causer un probléme dans des bd sql
    private String desc;
    @Column(unique = true, length = 20)
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "USERS_ROLES")
    @ToString.Exclude /// @Data de lombok execute la methode toString () a tous les attributs, donc lorsque on
    //affiche avec System.out.println les attributs il emchene un bouble avec l'association manyToMany
    //c'est pourquoi en ajoute l'annotation @ToString.Exclude
    //c'est pourquoi en ajoute l'annotation @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users = new ArrayList<>();
}
