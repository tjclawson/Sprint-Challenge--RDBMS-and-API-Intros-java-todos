package com.tjclawson.sprintchallengerdbmsandapi.services;

import com.tjclawson.sprintchallengerdbmsandapi.models.Todo;

public interface TodoService {

    Todo findTodoById(long id);

    Todo save(Todo todo, long userid);

    Todo update(Todo todo, long todoid);
}
