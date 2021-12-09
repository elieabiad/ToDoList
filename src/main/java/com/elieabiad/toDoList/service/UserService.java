package com.elieabiad.toDoList.service;

import com.elieabiad.toDoList.model.ToDoItem;
import com.elieabiad.toDoList.model.User;
import com.elieabiad.toDoList.model.exception.UnderAgeOverFiveToDoItemsException;
import com.elieabiad.toDoList.model.exception.UserNotFoundException;
import com.elieabiad.toDoList.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserDao userDao;
    private final ToDoItemService toDoItemService;

    @Autowired
    public UserService(UserDao userDao, ToDoItemService toDoItemService) {
        this.userDao = userDao;
        this.toDoItemService = toDoItemService;
    }
    public User addUser(User user){
        return userDao.save(user);
    }
    public List<User> getUsers(){
        return StreamSupport
                .stream(userDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
/*    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
    }*/
    public User getUser(Long userId){
        Optional<User> optionalUser = userDao.findById(userId);
        if(!optionalUser.isPresent()){ //https://www.youtube.com/watch?v=eQsXQBiXXsg different exception
            throw new UserNotFoundException("User is not available!");
        }
        return optionalUser.get();
    }
    public User deleteUser(Long id){
        User user = getUser(id);
        userDao.delete(user);
        return user;
    }
    @Transactional
    public User editUser(Long id, User user){
        User userToEdit = getUser(id);
        userToEdit.setName(user.getName());
        userToEdit.setBirthdate(user.getBirthdate());
        return userToEdit;//https://www.youtube.com/watch?v=eQsXQBiXXsg 1:05:00 @Transactional
        //return userRepository.save(userToEdit);

    }
    @Transactional
    public User addToDoItemToUser(Long userId, Long toDoItemId){
        User user = getUser(userId);
        ToDoItem toDoItem = toDoItemService.getToDoItem(toDoItemId);
        Period age = Period.between(user.getBirthdate(), LocalDate.now());
        if(user.getToDoItems().size()>4 && age.getYears()<18) {
            //toDoItemService.deleteToDoItem(toDoItemId);
            throw new UnderAgeOverFiveToDoItemsException("This user is underage and already has 5 to do items.");
        }else{
            user.addToDoItem(toDoItem);
        }
        return user;
    }
    @Transactional
    public User removeToDoItemFromUser(Long userId, Long toDoItemId){
        User user = getUser(userId);
        ToDoItem toDoItem = toDoItemService.getToDoItem(toDoItemId);
        user.removeToDoItem(toDoItem);
        return user;
    }
}
