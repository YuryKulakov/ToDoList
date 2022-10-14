package com.todo.todolist.models;

import com.todo.todolist.persistance.entity.ToDo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todolist")
public class ToDoList {


    public ToDoList() {
    }

    public ToDoList (ToDo todo){
        this.id= todo.getId();
        this.description= todo.getDescription();
        this.username=todo.getUser().getUsername();
        this.dateNow=todo.getDateNow();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private boolean done;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNow;
    private int priority;
    @Column(name = "username")
    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getDateNow() {
        return dateNow;
    }

    public void setDateNow(LocalDate dateNow) {
        this.dateNow = dateNow;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
