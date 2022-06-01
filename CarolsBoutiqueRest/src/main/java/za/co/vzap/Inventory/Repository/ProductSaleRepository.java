package za.co.vzap.Inventory.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Sale.Model.ProductSale;
import za.co.vzap.Sale.Repository.RepositoryBase;

public class ProductSaleRepository  extends RepositoryBase<ProductSale> {
    private static String tableName = "ProductSale";

    public ProductSaleRepository( ) {
        super(tableName, null);
    }

    @Override
    public boolean add(ProductSale productSale) {
        try {
            ps = con.prepareStatement("INSERT INTO " + tableName + "(productId, saleId) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productSale.getProductId());
            ps.setString(2,productSale.getSaleId());

            rowsAffected = ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                productSale.Id = keys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Product Sale Repository class at the add(ProductSale productSale) method.");
            throw new RuntimeException(e);
        }
        
        return rowsAffected == 1;
    }

    @Override
    public boolean update(ProductSale productSale) {
        try {
            ps = con.prepareStatement("Update " + tableName + " set productId = ?, saleId = ? WHERE id = ?");// table name is missing and check the column names in the table.
            ps.setString(1, productSale.getProductId());
            ps.setString(2,productSale.getSaleId());
            ps.setInt(3, productSale.Id);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Product Sale Repository class at the update(ProductSale productSale) method.");
            throw new RuntimeException(e);
        }
        
        return rowsAffected == 1;
    }

    @Override
    public ProductSale getById(int Id) {
        ProductSale productSale = null;
        
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
            ps.setInt(1,Id);
            rs = ps.executeQuery();

            if(rs.next()){
                productSale = new ProductSale(
                        rs.getString("productId"),
                        rs.getString("saleId")
                );
                
                productSale.Id = rs.getInt("id");
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Product Sale Repository class at the getById(int Id) method");
            throw new RuntimeException(e);
        }

        return productSale;
    }

    @Override
    public ProductSale getById(String id) {
        return null;
    }//No String ID

    @Override
    protected List<ProductSale> executeQuery(String statement) {
        List<ProductSale> productSales = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    
                    ProductSale productSale = new ProductSale(
                            rs.getString("productID"),
                            rs.getString("saleId")
                    );

                    productSale.Id = id;
                    productSales.add(productSale);
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

        return productSales;
    }
}

   
