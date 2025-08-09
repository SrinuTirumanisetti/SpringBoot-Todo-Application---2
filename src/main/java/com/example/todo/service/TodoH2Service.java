package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoH2Service implements TodoRepository{

    @Autowired
    public JdbcTemplate db;

    @Override
    public List<Todo> getTodoList(){
        List<Todo> TodoList = db.query("SELECT * FROM TODOLIST",new TodoRowMapper());
        return new ArrayList<>(TodoList);
    }

    @Override
    public Todo addTodo(Todo todo) {
        db.update(
            "INSERT INTO TODOLIST (todo, status, priority) VALUES (?, ?, ?)",
            todo.getTodo(),
            todo.getStatus(),
            todo.getPriority()
        );

        Todo savedTodo = db.queryForObject(
            "SELECT * FROM TODOLIST ORDER BY id DESC LIMIT 1",
            new TodoRowMapper()
        );

        return savedTodo;
    }

}
