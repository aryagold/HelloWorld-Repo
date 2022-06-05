package za.co.vzap.Inventory.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Inventory.Model.ProductCategory;

public class ProductCategoryRepository extends RepositoryBase<ProductCategory> {
    private static String tableName = "productcategory";

    public ProductCategoryRepository( ) {
        super(tableName, null);
    }

    @Override
    public int add(ProductCategory productCategory) {
        if(con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(productId, categoryId) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, productCategory.getProductId());
                ps.setString(2, productCategory.getCategoryId());

                rowsAffected = ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()){
                    productCategory.Id = keys.getInt(1);
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
        
        return productCategory.Id;
    }

    @Override
    public boolean update(ProductCategory productCategory) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set productId = ?, categoryId = ? WHERE id = ?");
                ps.setString(1, productCategory.getProductId());
                ps.setString(2,productCategory.getCategoryId());
                ps.setInt(3, productCategory.Id);

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
    public ProductCategory getById(int Id) {
        ProductCategory productCategory = null;
            if(con != null) {
                try {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                    ps.setInt(1,Id);
                    rs = ps.executeQuery();

                    if(rs.next()){
                        productCategory = new ProductCategory(
                            rs.getString("productId"),
                            rs.getString("categoryId")
                        );
                
                        productCategory.Id = rs.getInt("id");
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
    
        return productCategory;
    }

    @Override
    public ProductCategory getById(String id) {
        return null;
    }

    @Override
    protected List<ProductCategory> executeQuery(String statement) {
        List<ProductCategory> productCategories = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    
                    ProductCategory productCategory = new ProductCategory(
                            rs.getString("productID"),
                            rs.getString("categoryId")
                    );

                    productCategory.Id = id;
                    productCategories.add(productCategory);
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

        return productCategories;
    }

    @Override
    public String add2(ProductCategory arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
