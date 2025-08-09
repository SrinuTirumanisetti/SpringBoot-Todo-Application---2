
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

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable("id") int id){
        return service.getTodoById(id);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        return service.updateTodo(id, todo);
    }

}
