package ru.geekbrains.controllers;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.services.RoleService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {
    @EJB
    private RoleService roleService;
    private RoleDto role;
    private List<RoleDto> roles;

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public String createRole() {
        this.role = new RoleDto();
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        roles = roleService.findAll();
    }

    public List<RoleDto> getAllRoles() {
        return roles;
    }

    public String editRole(RoleDto role) {
        this.role = role;
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public void deleteRole(RoleDto role) {
        roleService.deleteById(role.getId());
    }

    public String saveRole() {
        roleService.saveOrUpdate(role);
        return "/admin/role.xhtml?faces-redirect=true";
    }
}
