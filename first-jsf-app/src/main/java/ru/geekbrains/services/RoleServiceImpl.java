package ru.geekbrains.services;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.entities.Role;
import ru.geekbrains.repositories.RoleRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleServiceImpl implements RoleService, Serializable {

    @Inject
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleDto> getAllRoles() {
        return roleRepository.getAllRoles().stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> findAll() {
        return getAllRoles();
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id);
        if(role != null) return new RoleDto(roleRepository.findById(id));
        return null;
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(RoleDto role) {
        Role savedUser = roleRepository.saveOrUpdate(new Role(role));
        role.setId(savedUser.getId());
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Long countAll() {
        return null;
    }
}
