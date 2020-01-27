package dao.queries;

public class ToDoQuery {

    public static final String INSERT_TODO = "INSERT INTO todo" +
            "  (text, is_done, aim_date, user_id, title) VALUES " +
            " (?, ?, ?, ?, ?);";

    public static final String GET_TODO_BY_ID =
            "SELECT * FROM todo WHERE todo_id = (?)";

    public static final String GET_ALL_USER_TODO =
            "SELECT * FROM todo WHERE user_id = (?)";

    public static final String UPDATE_TODO = "UPDATE todo SET " +
            " text = (?), is_done = (?), aim_date = (?), " +
            " user_id = (?), title = (?) WHERE todo_id = (?)";

    public static final String DELETE_TODO_BY_ID = "DELETE FROM todo WHERE todo_id = (?)";
}
