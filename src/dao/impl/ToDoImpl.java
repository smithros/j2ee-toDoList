package dao.impl;

import config.JDBCUtils;
import dao.interfaces.ToDoDao;
import dao.queries.ToDoQuery;
import models.ToDo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoImpl implements ToDoDao {

    public ToDoImpl() {
    }

    @Override
    public void createToDo(ToDo todo) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ToDoQuery.INSERT_TODO)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getUsername());
            preparedStatement.setString(3, todo.getDescription());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            preparedStatement.setBoolean(5, todo.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    @Override
    public ToDo getToDoById(int todoId) {
        ToDo todo = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ToDoQuery.GET_TODO_BY_ID)) {
            preparedStatement.setLong(1, todoId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todo = new ToDo(id, title, username, description, targetDate, isDone);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return todo;
    }

    @Override
    public List<ToDo> getAllToDos() {
        List<ToDo> toDoList = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(ToDoQuery.GET_ALL_USER_TODO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                toDoList.add(new ToDo(id, title, username, description, targetDate, isDone));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return toDoList;
    }

    @Override
    public void updateTodo(ToDo todo) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(ToDoQuery.UPDATE_TODO)) {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getUsername());
            statement.setString(3, todo.getDescription());
            statement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            statement.setBoolean(5, todo.getStatus());
            statement.setLong(6, todo.getId());

            System.out.println("update: " + statement);

        }
    }

    @Override
    public void deleteTodo(int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(ToDoQuery.DELETE_TODO_BY_ID)) {
            statement.setInt(1, id);
        }
    }
}
