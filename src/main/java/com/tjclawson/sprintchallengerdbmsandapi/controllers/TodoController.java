package com.tjclawson.sprintchallengerdbmsandapi.controllers;

import com.tjclawson.sprintchallengerdbmsandapi.models.Todo;
import com.tjclawson.sprintchallengerdbmsandapi.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PutMapping(value = "/todo/{todoid}", consumes = {"application/*"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long todoid) {
        todoService.update(updateTodo, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
