import java.util.List;
import Wine.domain.User;

package Wine.dao;

/**
 *
 * @author susanna
 */

public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}