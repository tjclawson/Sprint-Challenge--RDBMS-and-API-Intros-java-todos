package com.tjclawson.sprintchallengerdbmsandapi.repositories;

import com.tjclawson.sprintchallengerdbmsandapi.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
