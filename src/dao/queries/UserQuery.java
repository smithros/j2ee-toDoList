package dao.queries;

public class UserQuery {
    public static final String INSERT_USER = "INSERT INTO users" +
            "  (user_name, user_surname, user_login, user_password) VALUES " +
            " (?, ?, ?, ?);";

    public static final String GET_USER_BY_NAME_PASSWORD =
            "SELECT * FROM users WHERE user_name = (?) AND user_password = (?)";

    public static final String GET_USER_BY_ID =
            "SELECT * FROM users WHERE user_id = (?)";
}
