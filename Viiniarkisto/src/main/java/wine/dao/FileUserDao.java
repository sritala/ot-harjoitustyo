package wine.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import wine.domain.User;

/**
 * UserDao's class, which enables to create a user and saves it for the file
 *
 */
public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    /**
     * saves user for the file
     *
     */
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + "\n");
            }
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * creates a new user
     *
     * @param user
     * @return returns user for login
     * @throws java.lang.Exception
     */
    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
    }
}
