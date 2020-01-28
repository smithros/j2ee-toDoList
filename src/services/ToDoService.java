package services;

import dao.impl.ToDoImpl;
import dao.interfaces.ToDoDao;
import models.ToDo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ToDoService {

    private ToDoDao todoDAO;

    public ToDoService(ToDoDao todoDAO) {
        this.todoDAO = new ToDoImpl();
    }

    public void listTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<ToDo> listTodo = todoDAO.getAllToDos();
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/todo-list.jsp");
        dispatcher.forward(request, response);
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ToDo existingTodo = todoDAO.getToDoById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);

    }

    public void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"), df);

        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        ToDo newTodo = new ToDo(title, username, description, targetDate, isDone);
        todoDAO.createToDo(newTodo);
        response.sendRedirect("list");
    }

    public void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println("id =  " + id);

        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        ToDo updateTodo = new ToDo(id, title, username, description, targetDate, isDone);

        todoDAO.updateTodo(updateTodo);

        response.sendRedirect("list");
    }

    public void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoDAO.deleteTodo(id);
        response.sendRedirect("list");
    }
}
