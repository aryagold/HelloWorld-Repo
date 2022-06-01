/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Repositories;

import co.vzap.Inventory.Model.Category;
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
public class CategoryRepository extends RepositoryBase<Category> {
    private static String tableName = "Category";
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rs;
    private int rowsAffected;

    public CategoryRepository() {
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
    public boolean add(Category category) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,category.getName());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                category.categoryId = keys.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Category Repository class at the add(Category category) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Category category) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(name) VALUES(?) WHERE ID = ?");
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
        throw new UnsupportedOperationException("This isn't supported yet");
    }

    @Override
    public Category getById(String id) {
        Category category = null;
        try {
            ps = con.prepareStatement("SELECT * FROM "+tableName+" WHERE ID = ?");
            ps.setString(1,id);
            rs = ps.executeQuery();

            if(rs.next()){
                category = new Category(
                        rs.getString("name")
                );
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
                    Category category = new Category(
                          rs.getString("name")
                    );
                    category.categoryId =  rs.getString("id");
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

