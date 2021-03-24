package ru.geekbrains.controllers;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.dto.UserDto;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {
    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    private UserDto user;
    private List<UserDto> users;
    private List<RoleDto> roles;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new UserDto();
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        roles = roleService.findAll();
        users = userService.findAll();
    }

    public List<RoleDto> getAllRoles(){
        return roles;
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public String editUser(UserDto user) {
        this.user = user;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDto user) {
        userService.deleteById(user.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/admin/user.xhtml?faces-redirect=true";
    }
}
