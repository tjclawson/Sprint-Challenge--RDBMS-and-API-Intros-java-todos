package com.tjclawson.sprintchallengerdbmsandapi.services;

import com.tjclawson.sprintchallengerdbmsandapi.models.Role;
import com.tjclawson.sprintchallengerdbmsandapi.models.Todo;
import com.tjclawson.sprintchallengerdbmsandapi.models.User;
import com.tjclawson.sprintchallengerdbmsandapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " does not exist"));
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());
        newUser.setUsername(user.getUsername());

        for (Todo t : user.getTodos()) {
            Todo newTodo = new Todo(t.getDescription(), t.getDatestarted(), t.isCompleted(), newUser);
            newUser.getTodos().add(newTodo);
        }

        for (Role r : user.getRoles()) {
            Role newRole = roleService.findRoleById(r.getRoleid());
            newUser.addRole(newRole);
        }

        return userRepository.save(newUser);
    }

    @Override
    public User update(User user, long id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (findUserById(id) != null) {
            userRepository.deleteById(id);
        }
    }
}
