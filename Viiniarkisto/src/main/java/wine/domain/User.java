package wine.domain;

/**
 * Class representing the user properties
 */
public class User {

    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Checks if two users are equal comparing their usernames
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }
}

