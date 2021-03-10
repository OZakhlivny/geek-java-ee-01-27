package controllers;

import dto.UserDto;
import services.UserService;

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
    private UserDto user;
    private List<UserDto> users;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new UserDto();
        return "/user_form.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userService.findAll();
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public String editUser(UserDto user) {
        this.user = user;
        return "/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDto user) {
        userService.deleteById(user.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect=true";
    }
}
