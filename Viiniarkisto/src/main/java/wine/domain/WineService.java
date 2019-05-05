package wine.domain;

import wine.dao.UserDao;
import wine.dao.WineDao;
import java.util.List;

/**
 * A class handling business logic
 */
public class WineService {

    private WineDao wineDao;
    private UserDao userDao;
    private User loggedIn;

    public WineService(WineDao wineDao, UserDao userDao) {

        this.userDao = userDao;
        this.wineDao = wineDao;
    }

    /**
     * Adding a new wine
     *
     * @param year production year of the wine
     * @param country production country of the wine
     * @param name name of the wine
     * @param grape grape of the wine
     * @param food food which compliments the wine
     * @throws java.lang.Exception
     */
    public void createWine(int year, String country, String name, String grape, String food) throws Exception {
        String user = loggedIn.getUsername();
        Wine wine = new Wine(year, country, name, user, grape, food);
        wineDao.create(wine);
    }

    public List<Wine> getWines() {
        return wineDao.getAll();
    }

    /**
     * Removing the wine
     *
     * @param name name of the removed wine
     * @throws java.lang.Exception
     */
    public void delete(String name) throws Exception {
        wineDao.delete(name);
    }

    /**
     * Log in by username
     *
     * @param username username
     *
     * @return returns true or false depending on if the username was found
     */
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;

        }
        loggedIn = user;

        return true;
    }

    /**
     * Logged user
     *
     * @return returns logged in user
     */
    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * Log out
     */
    public void logout() {
        loggedIn = null;
    }

    /**
     * Modifying the wine
     *
     * @param id id of the wine
     * @param year production year of the wine
     * @param country production country of the wine
     * @param name name of the wine
     * @param grape grape of the wine
     * @param food food which compliments the wine
     * @throws java.lang.Exception
     */
    public void updateWine(int id, int year, String country, String name, String grape, String food) throws Exception {
        wineDao.updateWine(id, year, country, name, grape, food);
    }

    /**
     * creating of a new user
     *
     * @param username username
     *
     * @return true if no duplicated username was found
     * @throws java.lang.Exception
     */
    public boolean createUser(String username) throws Exception {
        if (userDao.findByUsername(username) != null) {
            System.out.println("Käyttäjä jo olemassa, valitse toinen käyttäjänimi");
            return false;
        }
        User newUser = new User(username);
        userDao.create(newUser);
        login(newUser.getUsername());
        return true;
    }

}
