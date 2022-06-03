package za.co.vzap.Inventory.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Inventory.Model.Size;

public class SizeRepository extends RepositoryBase<Size> {
    private static String tableName = "size";

    public SizeRepository( ) {
        super(tableName, null);
    }
    
    @Override
    public boolean add(Size size) {
        if(con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(size) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, size.getSize());
                
                rowsAffected = ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()){
                    size.Id = keys.getInt(1);
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
        
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Size size) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set size = ? WHERE id = ?");
                ps.setString(1, size.getSize());
                ps.setInt(2, size.Id);

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
    public Size getById(int Id) {
         Size size = null;
            if(con != null) {
                try {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                    ps.setInt(1,Id);
                    rs = ps.executeQuery();

                    if(rs.next()){
                        size = new Size(
                            rs.getString("size")
                        );
                
                        size.Id = rs.getInt("id");
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
    
            return size;
    }
    
   

    @Override
    public Size getById(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<Size> executeQuery(String statement) {
        List<Size> sizes = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    
                    Size size = new Size(
                            rs.getString("size")
                    );

                    size.Id = id;
                    sizes.add(size);
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

        return sizes;
    }
}
