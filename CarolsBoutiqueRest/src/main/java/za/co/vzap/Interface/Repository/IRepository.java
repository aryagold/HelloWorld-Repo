package za.co.vzap.Interface.Repository;

import java.util.List;

public interface IRepository<IEntity> {
    
    boolean add(IEntity entity);

    boolean update(IEntity entity);

    IEntity getById(int Id);

    IEntity getById(String id);

    boolean deleteById(int Id);

    boolean deleteById(String id);

    List<IEntity> getWhere(String field, String value);

    List<IEntity> getWhere(String field, int value);

    List<IEntity> getAll();
    
}
