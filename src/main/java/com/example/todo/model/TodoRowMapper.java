package com.example.todo.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setTodo(rs.getString("todo"));
        todo.setStatus(rs.getString("priority"));
        todo.setPriority(rs.getString("status"));
        return todo;
    }
}
