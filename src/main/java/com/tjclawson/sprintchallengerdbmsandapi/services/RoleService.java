package com.tjclawson.sprintchallengerdbmsandapi.services;

import com.tjclawson.sprintchallengerdbmsandapi.models.Role;

public interface RoleService {

    Role findRoleById(long id);

    Role save(Role role);
}
