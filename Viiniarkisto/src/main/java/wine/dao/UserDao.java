
package wine.dao;

/**
 * WineServicen rajapinta
 */

import java.util.List;
import wine.domain.User;


public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}
  

