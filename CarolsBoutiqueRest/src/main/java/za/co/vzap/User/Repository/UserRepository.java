package za.co.vzap.User.Repository;

import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.User.Model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.User.Model.RoleEnum;

public class UserRepository extends RepositoryBase<User> {
    private static String tableName = "user";
    private static final String idPrefix = "US";
    
    public UserRepository() {
        super(tableName, idPrefix);
    }

    @Override
    public boolean add(User user) {
         if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(id, name, email, role, branchId, password) values(?, ?, ?, ?, ?, ?)");
                ps.setString(1, getNextCode());
                ps.setString(2, user.getName());
                ps.setString(3, user.getEmail());
                ps.setInt(4, user.getRole().getValue());
                ps.setString(5, user.getBranchId());
                ps.setString(6, user.getPassword());
               
                rowsAffected = ps.executeUpdate();

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(ps != null) {
                    try {
                        ps.close();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return rowsAffected == 1;
    }

    @Override
    public boolean update(User user) {
       if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set name = ?, email = ?, branchId = ?, password = ?, role = ? where id = ?");
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getBranchId());
                ps.setString(4, user.getPassword());
                ps.setInt(5, user.getRole().getValue());
                ps.setString(6, user.userId);
               
                rowsAffected = ps.executeUpdate();

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(ps != null) {
                    try {
                        ps.close();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return rowsAffected == 1;
    }

    @Override
    public User getById(int Id) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getById(String Id) {
        User user = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    user = new User(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("branchId"),
                            rs.getString("password"),
                            RoleEnum.ofStatusCode(rs.getInt("role"))
                              
                    );
                    
                    user.userId = rs.getString("id");
                      
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(ps != null) {
                    try {
                        ps.close();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return user;
    }
    
    @Override
    protected List<User> executeQuery(String statement) {
        List<User> users = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    
                    User user = new User(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("branchId"),
                            rs.getString("password"),
                            RoleEnum.ofStatusCode(rs.getInt("role"))
                              
                    );

                    users.add(user);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(ps != null) {
                    try {
                        ps.close();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return users;
    }
}
