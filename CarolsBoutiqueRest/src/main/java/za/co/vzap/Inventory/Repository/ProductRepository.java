package za.co.vzap.Inventory.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Inventory.Model.Product;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class ProductRepository  extends RepositoryBase<Product> {
    private static String tableName = "Product";
    private static String idPrefix = "PR";

    public ProductRepository() {
        super(tableName, idPrefix);
    }

    @Override
    public String add2(Product product) {
        String id = getNextCode();
        
        product.productId = id;
        
        if(con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(id, name, price) VALUES (?,?,?)");
                ps.setString(1, id);
                ps.setString(2, product.getName());
                ps.setDouble(3, product.getPrice());
            
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
        
        return product.productId;
    }

    @Override
    public boolean update(Product product) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set name = ?, price = ? WHERE id = ?");
                ps.setString(1,product.getName());
                ps.setDouble(2,product.getPrice());
                ps.setString(3, product.productId);
            
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
    public Product getById(int Id) {
       throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public Product getById(String Id) {
        Product product = null;
        
        if(con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM product WHERE id = '" + Id + "'");
                rs = ps.executeQuery();

                if(rs.next()){
                    product = new Product(
                        rs.getString("name"),
                        rs.getLong("price")
                    );
                
                    product.productId = rs.getString("id");
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
            
        return product;
    }

    @Override
    protected List<Product> executeQuery(String statement) {
        List<Product> products = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    String id = rs.getString("id");
                    
                    Product product = new Product(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                    
                    product.productId = id;
                    products.add(product);
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

        return products;
    }

    @Override
    public int add(Product arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

