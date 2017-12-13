package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<E,K> {

    E update(E entity) throws  SQLException;
    E getById(K id) throws  SQLException;
    boolean delete(K id) throws  SQLException;
    boolean create(E entity) throws SQLException;
}
