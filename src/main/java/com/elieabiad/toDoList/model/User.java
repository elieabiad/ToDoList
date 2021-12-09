package com.elieabiad.toDoList.model;

import com.elieabiad.toDoList.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data   //lombok annotation to create getters, setters and constructors
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id")
    private List<ToDoItem> toDoItems = new ArrayList<>();

    public void addToDoItem(ToDoItem toDoItem){
        toDoItems.add(toDoItem);
    }
    public void removeToDoItem(ToDoItem toDoItem){
        toDoItems.remove(toDoItem);
    }
    public static User from(UserDto userDto){
        User user = new User();
        //user.setUserId(userDto.getId()); //only the name https://www.youtube.com/watch?v=eQsXQBiXXsg 54:00
        user.setName(userDto.getName());
        user.setBirthdate(userDto.getBirthdate());
        return user;
    }
}
