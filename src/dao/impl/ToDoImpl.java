package dao.impl;

import config.JDBCUtils;
import dao.interfaces.ToDoDao;
import dao.queries.ToDoQuery;
import models.ToDo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoImpl implements ToDoDao {

    @Override
    public ToDo getToDoById(int todoId) {
        ToDo toDo = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ToDoQuery.GET_TODO_BY_ID)) {

            preparedStatement.setInt(1, todoId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("todo_id");
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                String text = rs.getString("text");
                LocalDate aimDate = rs.getDate("aim_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                toDo = new ToDo(id, userId, title, text, aimDate, isDone);
            }

        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return toDo;
    }

    @Override
    public List<ToDo> getAllToDos() {
        List<ToDo> toDoList = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ToDoQuery.GET_ALL_USER_TODO)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("todo_id");
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                String text = rs.getString("text");
                LocalDate aimDate = rs.getDate("aim_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                toDoList.add(new ToDo(id, userId, title, text, aimDate, isDone));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return toDoList;
    }

    @Override
    public void createToDo(ToDo todo) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ToDoQuery.INSERT_TODO)) {

            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setInt(2, todo.getUserId());
            preparedStatement.setString(3, todo.getText());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getAimDate()));
            preparedStatement.setBoolean(5, todo.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    @Override
    public void updateTodo(ToDo todo) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(ToDoQuery.UPDATE_TODO);) {
            statement.setString(1, todo.getTitle());
            statement.setInt(2, todo.getUserId());
            statement.setString(3, todo.getText());
            statement.setDate(4, JDBCUtils.getSQLDate(todo.getAimDate()));
            statement.setBoolean(5, todo.getStatus());
            statement.setInt(6, todo.getTodoId());
        }
    }

    @Override
    public void deleteTodo(int todoId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(ToDoQuery.DELETE_TODO_BY_ID);) {
            statement.setInt(1, todoId);
        }
    }
}
