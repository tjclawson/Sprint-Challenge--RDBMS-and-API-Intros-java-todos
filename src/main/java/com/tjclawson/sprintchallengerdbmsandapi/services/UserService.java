package com.tjclawson.sprintchallengerdbmsandapi.services;

import com.tjclawson.sprintchallengerdbmsandapi.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(long id);

    User save(User user);

    User update(User user, long id);

    void delete(long id);


}
