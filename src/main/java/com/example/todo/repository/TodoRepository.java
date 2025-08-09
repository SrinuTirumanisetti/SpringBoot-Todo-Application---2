package com.example.todo.repository;

import com.example.todo.model.Todo;
import java.util.List;

public interface TodoRepository {
    List<Todo> getTodoList();
    Todo addTodo(Todo todo);
    Todo getTodoById(int id);
    Todo updateTodo(int id, Todo todo);
    void deleteTodo(int id);
}
