package ru.geekbrains.dto;

import ru.geekbrains.entities.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private Set<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, String login, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public UserDto(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getLogin(), user.getPassword());
        this.roles = new HashSet<>();
        user.getRoles().forEach(r -> this.roles.add(new RoleDto(r)));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto = {" + "id = " + id + ", login = '" + login + "\'" + "}";
    }
}
