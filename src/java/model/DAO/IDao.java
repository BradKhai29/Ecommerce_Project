package model.DAO;

import java.util.Map;
import java.util.Optional;

/**
 *
 * @author This PC
 */
public interface IDao<T> {
    
    Optional<T> get(int id);
    
    Map<String, T> getAll();
    
    void insert(T obj);
    
    /**
     * Specified the Field and Value to be updated
     * @param id
     * @param updateField
     * @param updateValue 
     */
    void update(int id, String updateField, String updateValue);
    
    /**
     * 
     * @param id
     * @param updateValue : String 
     */
    void update(int id, String... updateValue);
    
    void delete(int id);
}
