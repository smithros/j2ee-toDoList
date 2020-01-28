package dao.interfaces;

import models.ToDo;

import java.sql.SQLException;
import java.util.List;

public interface ToDoDao {

    ToDo getToDoById(int todoId);

    List<ToDo> getAllToDos();

    void createToDo(ToDo todo) throws SQLException;

    boolean updateTodo(ToDo todo) throws SQLException;

    boolean deleteTodo(int todoId) throws SQLException;
}
