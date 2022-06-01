/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Repositories;

import co.vzap.Sale.Model.ProductSale;
import co.vzap.Sale.Repository.RepositoryBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macpe
 */
public class ProductSaleRepository  extends RepositoryBase<ProductSale> {
    private static String tableName = "ProductSale";
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rs;
    private int rowsAffected;

    public ProductSaleRepository( ) {
        super(tableName);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/carolsboutique";
        try {
            con = DriverManager.getConnection(url,"root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(ProductSale productSale) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(productId,saleId) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
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
            ps = con.prepareStatement("INSERT INTO " +tableName+"(productId,saleId) VALUES(?,?) WHERE id = ?",Statement.RETURN_GENERATED_KEYS);// table name is missing and check the column names in the table.
            ps.setString(1, productSale.getProductId());
            ps.setString(2,productSale.getSaleId());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                productSale.Id = keys.getInt(1);
            }
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
            ps = con.prepareStatement("SELECT * FROM "+tableName+" WHERE id = ?");
            ps.setInt(1,Id);
            rs = ps.executeQuery();

            if(rs.next()){
                productSale = new ProductSale(
                        rs.getString("productID"),
                        rs.getString("saleId")
                );
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
                    ProductSale productSale = new ProductSale(
                            rs.getString("productID"),
                            rs.getString("saleId")
                    );

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

   
