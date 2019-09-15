package dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CRUDInterface<T> {

    Optional<T> read(Integer id);

    List<T> getAll();

    void create(T t);

    void delete(Integer id);

    void update(T t, String atribute, Map<String, Object> params);
}
