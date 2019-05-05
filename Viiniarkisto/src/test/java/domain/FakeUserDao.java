package domain;

import java.util.ArrayList;
import java.util.List;
import wine.dao.UserDao;
import wine.domain.User;

public class FakeUserDao implements UserDao {

    List<User> users = new ArrayList<>();

    public FakeUserDao() {
        users.add(new User("testertester"));
    }

    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

}
