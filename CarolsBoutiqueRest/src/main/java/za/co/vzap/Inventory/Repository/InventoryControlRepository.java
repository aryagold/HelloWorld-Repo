package za.co.vzap.Inventory.Repository;

import java.sql.ResultSet;
import za.co.vzap.Customer.Repository.CustomerRepository;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Inventory.Model.InventoryControl;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class InventoryControlRepository extends RepositoryBase<InventoryControl> {
    private static String tableName = "InventoryControl";

    public InventoryControlRepository() {
        super(tableName, null);
    }

    @Override
    protected List<InventoryControl> executeQuery(String statement) {
        List<InventoryControl> controls = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement(statement);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    
                    InventoryControl iv = new InventoryControl(
                            rs.getString("userId"),
                            rs.getString("productId"),
                            rs.getTimestamp("date"),
                            rs.getInt("quantityBefore"),
                            rs.getInt("incomingQuantity"),
                            rs.getInt("newStockQuantity"),
                            rs.getBoolean("posted")
                    );
                    
                    iv.Id = id;
                    controls.add(iv);
                }

            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }

        }
        return controls;
    }

    @Override
    public int add(InventoryControl entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(userId, productId, date, quantityBefore, incomingQuantity, newStockQuantity, posted) values(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entity.getUserId());
                ps.setString(2, entity.getProductId());
                ps.setTimestamp(3, entity.getDate());
                ps.setInt(4, entity.getQuantityBefore());
                ps.setInt(5, entity.getNewStockQuantity());
                ps.setBoolean(6, entity.isPosted());
                
                rowsAffected = ps.executeUpdate();
                
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    entity.Id = keys.getInt(1);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }

        }
        
        return entity.Id;
    }

    @Override
    public boolean update(InventoryControl entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("UPDATE " + tableName + "SET userId = ?, productId = ?, date = ?, quantitybefore = ?, incomingquantity = ?, newstockquantity = ?, posted = ? where id = ?");
                ps.setString(1, entity.getUserId());
                ps.setString(2, entity.getProductId());
                ps.setTimestamp(3, entity.getDate());
                ps.setInt(4, entity.getQuantityBefore());
                ps.setInt(5, entity.getIncomingQuantity());
                ps.setInt(6, entity.getNewStockQuantity());
                ps.setBoolean(7, entity.isPosted());
                ps.setInt(8, entity.Id);
                
                rowsAffected = ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }
        }
        
        return rowsAffected == 1;
    }

    @Override
    public InventoryControl getById(int Id) {
        InventoryControl iv = null;
        
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                ps.setInt(1, Id);
                
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    iv = new InventoryControl(
                            rs.getString("userId"),
                            rs.getString("productId"),
                            rs.getTimestamp("date"),
                            rs.getInt("quantityBefore"),
                            rs.getInt("incomingQuantity"),
                            rs.getInt("newStockQuantity"),
                            rs.getBoolean("posted")
                    );
                    
                    iv.Id = rs.getInt("id");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }
        }
        
        return iv;
    }

    @Override
    public InventoryControl getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add2(InventoryControl arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
