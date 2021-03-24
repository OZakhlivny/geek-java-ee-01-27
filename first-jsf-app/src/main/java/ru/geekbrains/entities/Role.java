package ru.geekbrains.entities;

import ru.geekbrains.dto.RoleDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = "findAllRoles", query = "from Role"),
        @NamedQuery(name = "countAllRoles", query = "select count(*) from Role"),
        @NamedQuery(name = "deleteByIdRole", query = "delete from Role r where r.id = :id")
})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Role(RoleDto role) {
        this.id = role.getId();
        this.name = role.getName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
