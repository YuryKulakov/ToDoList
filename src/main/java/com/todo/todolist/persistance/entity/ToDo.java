package com.todo.todolist.persistance.entity;

import com.todo.todolist.models.ToDoList;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNow;

    public ToDo() {
    }

    public ToDo(ToDoList toDoList) {
        this.id = toDoList.getId();
        this.description = toDoList.getDescription();
        this.dateNow = toDoList.getDateNow();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateNow() {
        return dateNow;
    }

    public void setDateNow(LocalDate dateNow) {
        this.dateNow = dateNow;
    }
}
