package co.vzap.User.Repository;

import co.vzap.Sale.Repository.RepositoryBase;
import co.vzap.User.Model.User;
import java.util.List;

public class UserRepository extends RepositoryBase<User> {
    private static String tableName = "user";
    private static final String idPrefix = "U";
    
    public UserRepository() {
        super(tableName);
    }

    @Override
    public boolean add(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getById(int user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getById(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<User> executeQuery(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
