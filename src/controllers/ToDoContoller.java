package controllers;

import dao.impl.ToDoImpl;
import models.ToDo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class ToDoContoller extends HttpServlet {

    private ToDoImpl toDoDao;

    public void init() {
        this.toDoDao = new ToDoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertToDo(request, response);
                    break;
                case "/delete":
                    deleteToDo(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateToDo(request, response);
                    break;
                case "/list":
                    listToDo(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void listToDo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<ToDo> listToDo = toDoDao.getAllToDos();
        request.setAttribute("listToDo", listToDo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("todo_id"));
        ToDo existingToDo = toDoDao.getToDoById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
        request.setAttribute("todo", existingToDo);
        dispatcher.forward(request, response);

    }

    private void insertToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String title = request.getParameter("title");
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String text = request.getParameter("text");

        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        ToDo newToDo = new ToDo(userId, title, text, LocalDate.now(), isDone);
        toDoDao.createToDo(newToDo);
        response.sendRedirect("list");
    }

    private void updateToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int todoId = Integer.parseInt(request.getParameter("id"));

        String title = request.getParameter("title");
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String text = request.getParameter("text");

        LocalDate aimDate = LocalDate.parse(request.getParameter("aim_date"));

        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        ToDo updateToDo = new ToDo(todoId, userId, title, text, aimDate, isDone);

        toDoDao.updateTodo(updateToDo);

        response.sendRedirect("list");
    }

    private void deleteToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        toDoDao.deleteTodo(id);
        response.sendRedirect("list");
    }
}
