package ru.geekbrains.dto;

import ru.geekbrains.entities.Role;

import java.io.Serializable;
import java.util.Objects;

public class RoleDto implements Serializable {
    private long id;

    private String name;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return name.equals(roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
