package com.tjclawson.sprintchallengerdbmsandapi.services;

import com.tjclawson.sprintchallengerdbmsandapi.models.Todo;
import com.tjclawson.sprintchallengerdbmsandapi.models.User;
import com.tjclawson.sprintchallengerdbmsandapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Qualifier("todoService")
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private UserService userService;

    public TodoServiceImpl(TodoRepository todoRepository, UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    @Override
    public Todo findTodoById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo with ID " + id + " not found"));
    }

    @Override
    public Todo save(Todo todo, long userid) {
        Todo newTodo = new Todo();

        newTodo.setDescription(todo.getDescription());
        newTodo.setDatestarted(todo.getDatestarted());
        newTodo.setUser(userService.findUserById(userid));

        return todoRepository.save(newTodo);
    }

    @Transactional
    @Override
    public Todo update(Todo todo, long todoid) {
        Todo currentTodo = findTodoById(todoid);

        if (todo.getDescription() != null) {
            currentTodo.setDescription(todo.getDescription());
        }

        if (todo.getDatestarted() != null) {
            currentTodo.setDatestarted(todo.getDatestarted());
        }

        if (todo.completedhasbeenset) {
            currentTodo.setCompleted(todo.isCompleted());
        }

        if (todo.getUser() != null) {
            currentTodo.setUser(userService.findUserById(todo.getUser().getUserid()));
        }
        return todoRepository.save(currentTodo);
    }
}
