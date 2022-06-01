/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.vzap.Inventory.Repository;

import co.vzap.Customer.Repository.CustomerRepository;
import co.vzap.Inventory.Model.InventoryControl;
import co.vzap.Sale.Repository.RepositoryBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryControlRepository extends RepositoryBase<InventoryControl> {

    private static String tableName = "InventoryControl";

    public InventoryControlRepository() {
        super(tableName);
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
                            rs.getString("employeeId"),
                            rs.getString("productId"),
                            rs.getTimestamp("date"),
                            rs.getInt("quantityBefore"),
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
    public boolean add(InventoryControl entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + " (employeeId, productId, date, quantityBefore, newStockQuantity, posted) values(null, ?, ?, ?)");
                ps.setString(1, entity.getEmployeeId());
                ps.setString(2, entity.getProductId());
                ps.setTimestamp(3, entity.getDate());
                ps.setInt(4, entity.getQuantityBefore());
                ps.setInt(5, entity.getNewStockQuantity());
                ps.setBoolean(6, entity.isPosted());
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
    public boolean update(InventoryControl entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("UPDATE " + tableName + "SET name = ?, email = ?, phoneNumber = ? where id = ?");
                ps.setString(1, entity.getEmployeeId());
                ps.setString(2, entity.getProductId());
                ps.setTimestamp(3, entity.getDate());
                ps.setInt(4, entity.getQuantityBefore());
                ps.setInt(5, entity.getNewStockQuantity());
                ps.setBoolean(6, entity.isPosted());
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
                            rs.getString("employeeId"),
                            rs.getString("productId"),
                            rs.getTimestamp("date"),
                            rs.getInt("quantityBefore"),
                            rs.getInt("newStockQuantity"),
                            rs.getBoolean("posted")
                    );
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

}
