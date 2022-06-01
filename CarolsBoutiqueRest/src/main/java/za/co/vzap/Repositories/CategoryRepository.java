package za.co.vzap.Repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Inventory.Model.Category;
import za.co.vzap.Sale.Repository.RepositoryBase;

public class CategoryRepository extends RepositoryBase<Category> {
    private static String tableName = "Category";
    private static final String idPrefix = "CA";
    
    public CategoryRepository() {
        super(tableName, idPrefix);
    }

    @Override
    public boolean add(Category category) {
        try {
            ps = con.prepareStatement("INSERT INTO " + tableName + "(id, name) VALUES(?, ?)");
            ps.setString(1, getNextCode());
            ps.setString(2, category.getName());

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Category Repository class at the add(Category category) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Category category) {
        try {
            ps = con.prepareStatement("Update " + tableName + " set name = ? where id = ?");
            ps.setString(1, category.getName());
            ps.setString(2,category.categoryId);
            
            rowsAffected = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Category Repository class at the update(Category category) method.");
            throw new RuntimeException(e);
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
            } catch (SQLException e) {
                System.out.println("SQLException thrown in the Category Repository class at the getById(int Id) method");
            throw new RuntimeException(e);
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
                if(ps != null) {
                    try {
                        ps.close();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return categories;
    }
    
}

