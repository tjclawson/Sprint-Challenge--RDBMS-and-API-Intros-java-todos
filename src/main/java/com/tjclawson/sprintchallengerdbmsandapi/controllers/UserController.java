package com.tjclawson.sprintchallengerdbmsandapi.controllers;

import com.tjclawson.sprintchallengerdbmsandapi.models.Todo;
import com.tjclawson.sprintchallengerdbmsandapi.models.User;
import com.tjclawson.sprintchallengerdbmsandapi.services.TodoService;
import com.tjclawson.sprintchallengerdbmsandapi.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private TodoService todoService;

    public UserController(@Qualifier("userService") UserService userService, @Qualifier("todoService") TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    // http://localhost:2019/users/users
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // http://localhost:2019/users/user/{userid}
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getUserById(@PathVariable long userid) {
        User myUser = userService.findUserById(userid);
        return new ResponseEntity<>(myUser, HttpStatus.OK);
    }

    // http://localhost:2019/users/user
    @PostMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser) {
        newUser = userService.save(newUser);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();

        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/users/todoo{userid}
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(@Valid @RequestBody Todo newTodo, @PathVariable long userid) {
        newTodo = todoService.save(newTodo, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/userid/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid) {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
