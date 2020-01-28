package controllers;

import dao.impl.ToDoImpl;
import dao.interfaces.ToDoDao;
import services.ToDoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class ToDoController extends HttpServlet {

    private ToDoService toDoService;
    private ToDoImpl toDoImpl;

    @Override
    public void init() {
        this.toDoService = new ToDoService(toDoImpl);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    toDoService.showNewForm(request, response);
                    break;
                case "/insert":
                    toDoService.insertTodo(request, response);
                    break;
                case "/delete":
                    toDoService.deleteTodo(request, response);
                    break;
                case "/edit":
                    toDoService.showEditForm(request, response);
                    break;
                case "/update":
                    toDoService.updateTodo(request, response);
                    break;
                case "/list":
                    toDoService.listTodo(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}