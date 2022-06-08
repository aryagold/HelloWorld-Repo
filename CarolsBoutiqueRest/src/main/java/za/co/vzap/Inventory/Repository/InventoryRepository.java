package za.co.vzap.Inventory.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Inventory.Model.Inventory;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class InventoryRepository extends RepositoryBase<Inventory> {
    private static String tableName = "Inventory";

    public InventoryRepository() {
        super(tableName, null);
    }

    @Override
    public int add(Inventory inventory) {
        try {
            ps = con.prepareStatement("INSERT INTO " + tableName+ "(branchId, sizeId, productid, barcode, quantity) VALUES (?, ?, ?, ?,?)",Statement.RETURN_GENERATED_KEYS);
            
            int sizeId = inventory.getSizeId();
            String productID = inventory.getProductID();
            
            ps.setString(1,inventory.getBranchId());
            ps.setInt(2, sizeId);
            ps.setString(3,productID);
            ps.setString(4, inventory.getBarcode());
            ps.setInt(5,inventory.getQuantity());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                inventory.Id = keys.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException error thrown in the Inventory Repository class at the add(Inventory inventory) method.");
            throw new RuntimeException(e);
        }
        
        return inventory.Id;
    }

    @Override
    public boolean update(Inventory inventory) {
        try {
            ps = con.prepareStatement("update " + tableName + " set branchID = ?, sizeId = ?, productid = ?, barcode = ?, quantity = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,inventory.getBranchId());
            ps.setInt(2, inventory.getSizeId());
            ps.setString(3,inventory.getProductID());
            ps.setString(4, inventory.getBarcode());
            ps.setInt(5,inventory.getQuantity());
            ps.setInt(6, inventory.Id);
            
            rowsAffected = ps.executeUpdate();

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
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
            
            ps.setInt(1, Id);
            
            rs = ps.executeQuery();

            if (rs.next()){
                inventory = new Inventory(
                        rs.getString("branchID"),
                        rs.getInt("sizeId"),
                        rs.getString("productid"),
                        rs.getString("barcode"),
                        rs.getInt("quantity")
                );
                
                inventory.Id = rs.getInt("id");
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
                    int id = rs.getInt("id");
                    
                    Inventory inventory = new Inventory(
                            rs.getString("branchID"),
                            rs.getInt("sizeId"),
                            rs.getString("productid"),
                            rs.getString("barcode"),
                            rs.getInt("quantity")
                    );

                    inventory.Id = id;
                    inventories.add(inventory);
                }//product sale branch category user, has string ID and this needs to be included in rs.getString().

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

    @Override
    public String add2(Inventory arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
    

