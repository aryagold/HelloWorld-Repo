package za.co.vzap.Inventory.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Inventory.Model.ProductCode;

public class ProductCodeRepository extends RepositoryBase<ProductCode> {
    private static String tableName = "productcode";

    public ProductCodeRepository( ) {
        super(tableName, null);
    }
    
    @Override
    public int add(ProductCode productCode) {
        if(con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(productId) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, productCode.getProductId());
                
                rowsAffected = ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()){
                    productCode.Id = keys.getInt(1);
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
        
        return productCode.Id;
    }

    @Override
    public boolean update(ProductCode productCode) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set productId = ? WHERE id = ?");
                ps.setString(1, productCode.getProductId());
                ps.setInt(2, productCode.Id);

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
    public ProductCode getById(int Id) {
         ProductCode productCode = null;
            if(con != null) {
                try {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                    ps.setInt(1,Id);
                    rs = ps.executeQuery();

                    if(rs.next()){
                        productCode = new ProductCode(
                            rs.getString("productId")
                        );
                
                        productCode.Id = rs.getInt("id");
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
    
            return productCode;
    }

    @Override
    public ProductCode getById(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<ProductCode> executeQuery(String statement) {
        List<ProductCode> productCodes = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    
                    ProductCode productCode = new ProductCode(
                            rs.getString("productID")
                    );

                    productCode.Id = id;
                    productCodes.add(productCode);
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

        return productCodes;
    }

    @Override
    public String add2(ProductCode arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
