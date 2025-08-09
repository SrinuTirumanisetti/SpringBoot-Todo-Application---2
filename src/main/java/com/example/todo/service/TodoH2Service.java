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

    @Override
    public Todo getTodoById(int id){
        try{
            Todo todo = db.queryForObject("SELECT * FROM TODOLIST WHERE id=?",new TodoRowMapper(),id);
            return todo;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
            if (todo.getTodo() != null) {
            db.update("UPDATE TODOLIST SET todo = ? WHERE id = ?", todo.getTodo(), id);
            }
            if (todo.getStatus() != null) {
                db.update("UPDATE TODOLIST SET status = ? WHERE id = ?", todo.getStatus(), id);
            }
            if (todo.getPriority() != null) {
                db.update("UPDATE TODOLIST SET priority = ? WHERE id = ?", todo.getPriority(), id);
            } 
            return getTodoById(id);
    }

}
