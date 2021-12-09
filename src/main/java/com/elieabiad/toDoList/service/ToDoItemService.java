package com.elieabiad.toDoList.service;

import com.elieabiad.toDoList.model.ToDoItem;
import com.elieabiad.toDoList.model.exception.ToDoItemNotFoundException;
import com.elieabiad.toDoList.dao.ToDoItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ToDoItemService {

    private final ToDoItemDao toDoItemDao;

    @Autowired
    public ToDoItemService(ToDoItemDao toDoItemDao) {
        this.toDoItemDao = toDoItemDao;
    }

    public ToDoItem addToDoItem(ToDoItem toDoItem){
        return toDoItemDao.save(toDoItem);
    }
    public List<ToDoItem> getToDoItems(){
        return StreamSupport
                .stream(toDoItemDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
/*    public ToDoItem getToDoItem(Long id){
        return toDoItemRepository.findById(id).orElseThrow(()->
                new ToDoItemNotFoundException(id));
    }*/
    public ToDoItem getToDoItem(Long toDoItemId){
        Optional<ToDoItem> optionalToDoItem = toDoItemDao.findById(toDoItemId);
        if(!optionalToDoItem.isPresent()){ //https://www.youtube.com/watch?v=eQsXQBiXXsg different exception
            throw new ToDoItemNotFoundException("ToDoItem is not available!");
        }
        return optionalToDoItem.get();
    }

    public ToDoItem deleteToDoItem(Long id){
        ToDoItem toDoItem = getToDoItem(id);
        toDoItemDao.delete(toDoItem);
        return toDoItem;
    }
    @Transactional
    public ToDoItem editToDoItem(Long id, ToDoItem toDoItem){
        ToDoItem toDoItemToEdit = getToDoItem(id);
        toDoItemToEdit.setTitle(toDoItem.getTitle());
        toDoItemToEdit.setDone(toDoItem.isDone());
        return toDoItemToEdit;
        //return toDoItemRepository.save(toDoItemToEdit);//https://www.youtube.com/watch?v=eQsXQBiXXsg 1:05:00 @Transactional
    }


}
