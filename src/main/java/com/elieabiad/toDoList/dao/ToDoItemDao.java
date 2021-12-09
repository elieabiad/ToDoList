package com.elieabiad.toDoList.dao;

import com.elieabiad.toDoList.model.ToDoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemDao extends CrudRepository<ToDoItem, Long> {

}
