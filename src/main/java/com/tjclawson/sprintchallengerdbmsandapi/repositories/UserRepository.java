package com.tjclawson.sprintchallengerdbmsandapi.repositories;

import com.tjclawson.sprintchallengerdbmsandapi.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
