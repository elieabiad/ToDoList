package com.elieabiad.toDoList.model.dto;

import com.elieabiad.toDoList.model.ToDoItem;
import com.elieabiad.toDoList.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private List<ToDoItemDto> toDoItemsDto = new ArrayList<>();

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setToDoItemsDto(user.getToDoItems().stream().map(ToDoItemDto::from).collect(Collectors.toList()));//https://www.youtube.com/watch?v=eQsXQBiXXsg 1:12:00
        return userDto;
    }

}
