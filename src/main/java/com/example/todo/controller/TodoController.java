package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    public TodoRepository todoService;

    // API 1 - Get all todos
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getTodoList();
    }

    // API 2 - Create new todo
    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    // API 3 - Get todo by ID
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable("id") int id) {
        return todoService.getTodoById(id);
    }

    // API 4 - Update todo by ID
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    // API 5 - Delete todo by ID
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") int id) {
        todoService.deleteTodo(id);
    }
}
