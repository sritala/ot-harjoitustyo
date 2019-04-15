/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wine.dao;

import Wine.domain.Wine;
import java.util.List;

/**
 *
 * @author susanna
 */
public interface WineDao {
    
    Wine create(Wine wine) throws Exception;

    List<Wine> getAll();
    
    void delete();
    
}
