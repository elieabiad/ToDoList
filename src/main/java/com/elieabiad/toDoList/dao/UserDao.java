package com.elieabiad.toDoList.dao;

import com.elieabiad.toDoList.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
}
