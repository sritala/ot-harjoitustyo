package wine.dao;

import wine.domain.Wine;
import java.util.List;

/**
 * WineServicen rajapinta
 */
public interface WineDao {

    Wine create(Wine wine) throws Exception;

    List<Wine> getAll();

    void delete(String name) throws Exception;

    void updateWine(int id, int year, String country, String name, String grape, String food) throws Exception;

}
