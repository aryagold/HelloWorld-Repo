package za.co.vzap.Inventory.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class CategoryRepository extends RepositoryBase<Category> {
    private static String tableName = "Category";
    private static final String idPrefix = "CA";
    
    public CategoryRepository() {
        super(tableName, idPrefix);
    }

    @Override
    public String add2(Category category) {
        String id = getNextCode();
        category.categoryId = id;
        
        if(con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(id, name) VALUES(?, ?)");
                ps.setString(1, id);
                ps.setString(2, category.getName());

                rowsAffected = ps.executeUpdate();

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }
        
        return id;
    }

    @Override
    public boolean update(Category category) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set name = ? where id = ?");
                ps.setString(1, category.getName());
                ps.setString(2,category.categoryId);
            
                rowsAffected = ps.executeUpdate();
            
            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }
        
        return rowsAffected == 1;
    }

    @Override
    public Category getById(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getById(String Id) {
        Category category = null;
        
        if(con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE ID = ?");
            
                ps.setString(1, Id);
            
                rs = ps.executeQuery();

                if(rs.next()) {
                    category = new Category(
                            rs.getString("name")
                    );
                    
                    category.categoryId = rs.getString("id");
                }
           } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return category;
    }
    
    @Override
    protected List<Category> executeQuery(String statement) {
         List<Category> categories = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    String id = rs.getString("id");
                    
                    Category category = new Category(
                            rs.getString("id"),
                          rs.getString("name")
                    );

                    category.categoryId = id;
                    categories.add(category);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
               
                closeStreams(rs, ps);
                
            }
        }

        return categories;
    }

    @Override
    public int add(Category arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

