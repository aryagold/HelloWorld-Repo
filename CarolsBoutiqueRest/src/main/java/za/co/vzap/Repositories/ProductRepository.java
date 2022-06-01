/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Repositories;

import co.vzap.Inventory.Model.Product;
import co.vzap.Sale.Repository.RepositoryBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
/**
 *
 * @author macpe
 */
public class ProductRepository  extends RepositoryBase<Product> {
    private static String tableName = "Product";
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rs;
    private int rowsAffected;

    public ProductRepository() {
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
    public boolean add(Product product) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(name,barcode,size,price) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,product.getName());
            ps.setString(2,product.getBarcode());
            ps.setString(3, product.getSize());
            ps.setDouble(4,product.getPrice());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                product.productId = keys.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Product Repository class at the add(Product product) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Product product) {
        try {
            ps = con.prepareStatement("INSERT INTO product(name,barcode,size,price) VALUES (?,?,?,?) WHERE id = ? ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,product.getName());
            ps.setString(2,product.getBarcode());
            ps.setString(3, product.getSize());
            ps.setDouble(4,product.getPrice());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                product.productId = keys.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Product Repository class at the update(Product product) method.");
            throw new RuntimeException(e);
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
        try {
            ps = con.prepareStatement("SELECT * FROM product WHERE id = "+Id);
            rs = ps.executeQuery();

            if(rs.next()){
                product = new Product(
                        rs.getString("name"),
                        rs.getString("barcode"),
                        rs.getString("size"),
                        rs.getLong("price"));
                product.productId = rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Product Repository class at the getById(int Id) method");
            throw new RuntimeException(e);
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
                    Product product = new Product(
                            rs.getString("name"),
                            rs.getString("barcode"),
                            rs.getString("size"),
                            rs.getDouble("price")
                    );
                    product.productId =rs.getString("id"); 
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
}

