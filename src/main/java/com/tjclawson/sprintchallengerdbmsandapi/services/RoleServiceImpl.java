package com.tjclawson.sprintchallengerdbmsandapi.services;

import com.tjclawson.sprintchallengerdbmsandapi.models.Role;
import com.tjclawson.sprintchallengerdbmsandapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Qualifier("roleService")
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with ID " + id + " not found"));
    }

    @Transactional
    @Override
    public Role save(Role role) {
        if (role.getUsers().size() > 0) {
            throw new EntityNotFoundException("Users not added through roles");
        }

        Role newRole = new Role();
        newRole.setUsers(new ArrayList<>());
        newRole.setRolename(role.getRolename());
        return roleRepository.save(newRole);
    }
}
