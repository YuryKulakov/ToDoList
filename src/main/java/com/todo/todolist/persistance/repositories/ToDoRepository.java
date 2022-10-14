package com.todo.todolist.persistance.repositories;

import com.todo.todolist.models.ToDoList;
import com.todo.todolist.persistance.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {

    @Query
    List<ToDoList> findTodoByUserId(@Param("userId")Long userId);
}
