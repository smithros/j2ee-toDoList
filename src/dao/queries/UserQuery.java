package dao.queries;

public class UserQuery {
    public static final String INSERT_USERS_SQL = "INSERT INTO users" +
            "  (first_name, last_name, username, password) VALUES " +
            " (?, ?, ?, ?);";
}
