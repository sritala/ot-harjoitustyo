
package Wine.dao;

/**
 *
 * @author susanna
 */

import java.util.List;
import Wine.domain.User;


public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}
  
