package com.elieabiad.toDoList.controller;

import com.elieabiad.toDoList.model.ToDoItem;
import com.elieabiad.toDoList.model.dto.ToDoItemDto;
import com.elieabiad.toDoList.service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/toDoItems")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;

    @Autowired
    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }
    @PostMapping
    // DTO = data transfer object
    public ResponseEntity<ToDoItemDto> addToDoItem(@RequestBody final ToDoItemDto toDoItemDto){
        ToDoItem toDoItem = toDoItemService.addToDoItem(ToDoItem.from(toDoItemDto));
        return new ResponseEntity<>(ToDoItemDto.from(toDoItem), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ToDoItemDto>> getToDoItems(){
        List<ToDoItem> toDoItems = toDoItemService.getToDoItems();
        List<ToDoItemDto> toDoItemsDto = toDoItems.stream().map(ToDoItemDto::from).collect(Collectors.toList());   //https://www.youtube.com/watch?v=eQsXQBiXXsg 42:30
        return new ResponseEntity<>(toDoItemsDto, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ToDoItemDto> getToDoItem(@PathVariable final Long id){
        ToDoItem toDoItem = toDoItemService.getToDoItem(id);
        return new ResponseEntity<>(ToDoItemDto.from(toDoItem), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ToDoItemDto> deleteToDoItem(@PathVariable final Long id){
        ToDoItem toDoItem = toDoItemService.deleteToDoItem(id);
        return new ResponseEntity<>(ToDoItemDto.from(toDoItem), HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<ToDoItemDto> editToDoItem(@PathVariable final Long id,
                                                    @RequestBody final ToDoItemDto toDoItemDto){
        ToDoItem editedToDoItem = toDoItemService.editToDoItem(id,ToDoItem.from(toDoItemDto));
        return new ResponseEntity<>(ToDoItemDto.from(editedToDoItem), HttpStatus.OK);
    }
}
