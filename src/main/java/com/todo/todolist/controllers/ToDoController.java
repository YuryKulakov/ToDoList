package com.todo.todolist.controllers;

import com.todo.todolist.models.ToDoList;
import com.todo.todolist.persistance.entity.ToDo;
import com.todo.todolist.services.ToDoService;
import com.todo.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ToDoController {

    @Autowired
    public ToDoService toDoService;
    @Autowired
    public UserService userService;

    @GetMapping("/home")
    public String homePage(Model model) {
        List<ToDoList> todos = toDoService.findTodoByUserId(userService.getCurrentUserId().orElseThrow(() -> new ResourceNotFoundException()));
        model.addAttribute("todos",todos);
        return "home.html";
    }

    @GetMapping("/todo/{id}")
    public String ToDo(@PathVariable("id") Long id, Model model) {
        ToDoList toDoList = toDoService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        model.addAttribute("todo", toDoList);
        return "createnewtask.html";
    }

    @GetMapping("/createnewtask")
    public String addToDo(Model model) {
        model.addAttribute("todo",new ToDoList());
        return "createnewtask.html";
    }

    @PostMapping("/createnewtask")
    public String addToDoPost(@ModelAttribute("todo") ToDoList toDoList) {
        toDoService.save(toDoList);
        return "redirect:/home";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteToDo (Model model, @PathVariable Long id){
        toDoService.delete(id);
        return "redirect:/home";
    }

}
