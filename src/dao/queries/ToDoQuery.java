package dao.queries;

public class ToDoQuery {

    public static final String INSERT_TODO = "INSERT INTO todo" +
            " (title, username, description, target_date, is_done) VALUES (?, ?, ?, ?, ?)";

    public static final String GET_TODO_BY_ID =
            "SELECT * FROM todo WHERE id = (?)";

    public static final String GET_ALL_USER_TODO =
            "SELECT * FROM todo";

    public static final String UPDATE_TODO = "UPDATE todo SET " +
            " title = (?), username = (?), description = (?), " +
            " target_date = (?), is_done = (?) WHERE id = (?)";

    public static final String DELETE_TODO_BY_ID = "DELETE FROM todo WHERE id = (?)";
}
