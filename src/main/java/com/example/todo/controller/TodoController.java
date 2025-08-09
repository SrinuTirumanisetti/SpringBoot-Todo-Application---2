
package com.example.todo.controller;

import com.example.todo.model.Todo;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.todo.service.TodoH2Service;

@RestController
public class TodoController{
    @Autowired
    public TodoH2Service service;

    @GetMapping("/todos")
    public List<Todo> getTodoList(){
        return service.getTodoList();
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo){
        return service.addTodo(todo);
    }
}
