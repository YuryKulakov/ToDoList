package com.todo.todolist.services;

import com.todo.todolist.models.ToDoList;
import com.todo.todolist.persistance.entity.ToDo;
import com.todo.todolist.persistance.repositories.ToDoRepository;
import com.todo.todolist.persistance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.todo.todolist.services.UserService.getCurrentUser;

@Transactional
@Service
public class ToDoService {


    @Autowired
    public ToDoRepository toDoRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public Optional<ToDoList> findById(Long id) {
        return toDoRepository.findById(id).map(ToDoList::new);
    }

    public List<ToDoList> findTodoByUserId(Long userId){
        return toDoRepository.findTodoByUserId(userId);
    }


    public void save(ToDoList toDoList) {
        getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .ifPresent(user -> {
                    ToDo toDo = new ToDo();
                    toDo.setId(toDoList.getId());
                    toDo.setDescription(toDoList.getDescription());
                    toDo.setDateNow(toDoList.getDateNow());
                    toDo.setUser(user);
                    toDoRepository.save(toDo);
                });
    }

    public void delete(Long id){
        Optional<ToDo> optional = toDoRepository.findById(id);
        if(optional.isPresent())
        toDoRepository.delete(optional.get());
    }

}
