package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@Service
public class TodoH2Service implements TodoRepository {

    @Autowired
    public JdbcTemplate db;

    @Override
    public List<Todo> getTodoList() {
        return db.query("SELECT * FROM TODOLIST", new TodoRowMapper());
    }

    @Override
    public Todo addTodo(Todo todo) {
        if (todo.getStatus() == null) {
            todo.setStatus("TO DO");
        }
        if (todo.getPriority() == null) {
            todo.setPriority("TO DO");
        }

        db.update(
            "INSERT INTO TODOLIST (todo, status, priority) VALUES (?, ?, ?)",
            todo.getTodo(),
            todo.getStatus(),
            todo.getPriority()
        );

        return db.queryForObject(
            "SELECT * FROM TODOLIST ORDER BY id DESC LIMIT 1",
            new TodoRowMapper()
        );
    }

    @Override
    public Todo getTodoById(int id) {
        try {
            return db.queryForObject(
                "SELECT * FROM TODOLIST WHERE id = ?",
                new TodoRowMapper(),
                id
            );
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
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

    @Override
    public void deleteTodo(int id) {
        int rows = db.update("DELETE FROM TODOLIST WHERE id = ?", id);
        if (rows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
    }
}