package ru.geekbrains.services;

import ru.geekbrains.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findById(Long id);

    void saveOrUpdate(RoleDto role);

    void deleteById(Long id);

    Long countAll();
}
