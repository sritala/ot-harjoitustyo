package Wine.dao;

import Wine.domain.Wine;
import java.util.List;

/**
 * WineServicen rajapinta
 */
public interface WineDao {

    Wine create(Wine wine) throws Exception;

    List<Wine> getAll();

    void delete(String name) throws Exception;

    void updateWine(int id, int Year, String country, String name) throws Exception;

}
