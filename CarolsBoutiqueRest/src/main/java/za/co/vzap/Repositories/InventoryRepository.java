/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Repositories;

import co.vzap.Inventory.Model.Inventory;
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
public class InventoryRepository extends RepositoryBase<Inventory> {
    private static String tableName = "Inventory";
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rs;
    private int rowsAffected;

    public InventoryRepository() {
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
    public boolean add(Inventory inventory) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(branchID,productID,quantity) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,inventory.getBranchId());
            ps.setString(2,inventory.getProductId());
            ps.setInt(3,inventory.getQuantity());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                inventory.Id = keys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Inventory Repository class at the add(Inventory inventory) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Inventory inventory) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(branchID,productID,quantity) VALUES (?,?,?) WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,inventory.getBranchId());
            ps.setString(2,inventory.getProductId());
            ps.setInt(3,inventory.getQuantity());
            ps.setInt(4,inventory.Id);

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()){
                inventory.Id = keys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Inventory Repository class at the update(Inventory inventory) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public Inventory getById(int Id) {
        Inventory inventory = null;
        try {
            ps = con.prepareStatement("SELECT * FROM "+tableName+" WHERE Id = ?");// table name is missing and check the column names in the table.
            rs = ps.executeQuery();

            if (rs.next()){
                inventory = new Inventory(
                        rs.getString("branchID"),
                        rs.getString("productID"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Inventory Repository class at the getById(int Id) method.");
            throw new RuntimeException(e);
        }
        return inventory;
    }

    @Override
    public Inventory getById(String id) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    protected List<Inventory> executeQuery(String statement) {
        List<Inventory> inventories = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    Inventory inventory = new Inventory(
                            rs.getString("branchID"),
                            rs.getString("productID"),
                            rs.getInt("quantity")
                    );

                    inventories.add(inventory);
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

        return inventories;
    }
}
    

