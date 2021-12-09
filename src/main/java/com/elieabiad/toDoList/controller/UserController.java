package com.elieabiad.toDoList.controller;

import com.elieabiad.toDoList.model.User;
import com.elieabiad.toDoList.model.dto.ToDoItemDto;
import com.elieabiad.toDoList.model.dto.UserDto;
import com.elieabiad.toDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody final UserDto userDto){
        User user = userService.addUser(User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDto> usersDto = users.stream().map(UserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable final Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(UserDto.from(user),HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable final Long id){
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(UserDto.from(user),HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable final Long id,
                                            @RequestBody final UserDto userDto){
        User user = userService.editUser(id,User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user),HttpStatus.OK);
    }
    @PostMapping(value = "{userId}/toDoItems/{toDoItemId}/add")
    public ResponseEntity<UserDto> addToDoItemToUser(@PathVariable final Long userId,
                                                     @PathVariable final Long toDoItemId){
        User user = userService.addToDoItemToUser(userId, toDoItemId);
        return new ResponseEntity<>(UserDto.from(user),HttpStatus.OK);
    }
    @DeleteMapping(value = "{userId}/toDoItems/{toDoItemId}/remove")
    public ResponseEntity<UserDto> removeToDoItemFromUser(@PathVariable final Long userId,
                                                     @PathVariable final Long toDoItemId){
        User user = userService.removeToDoItemFromUser(userId, toDoItemId);
        return new ResponseEntity<>(UserDto.from(user),HttpStatus.OK);
    }

}
