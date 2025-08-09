package com.example.todo.repository;

import java.util.*;
import com.example.todo.model.Todo;

public interface TodoRepository{
    List<Todo> getTodoList();
    Todo addTodo(Todo todo);
}