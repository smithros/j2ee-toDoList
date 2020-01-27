package dao.interfaces;

import models.User;

public interface UserDao {

    int register(User user);

    boolean validate(User user);
}
