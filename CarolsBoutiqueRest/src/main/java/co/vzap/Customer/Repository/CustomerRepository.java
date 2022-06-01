/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.vzap.Customer.Repository;

import co.vzap.Customer.Model.Customer;
import co.vzap.Sale.Repository.RepositoryBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepository extends RepositoryBase<Customer> {

    private static String tableName = "customer";

    public CustomerRepository() {
        super(tableName);
    }

    @Override
    protected List<Customer> executeQuery(String statement) {
        List<Customer> customers = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    Customer customer = new Customer(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phoneNumber")
                    );
                    customer.Id = id;
                    customers.add(customer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }

        }
        return customers;
    }

    @Override
    public boolean add(Customer entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + " (id, name, email, phoneNumber) values(null, ?, ?, ?)");
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getEmail());
                ps.setString(3, entity.getPhoneNumber());
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
    public boolean update(Customer entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("UPDATE " + tableName + "SET name = ?, email = ?, phoneNumber = ? where id = ?");
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getEmail());
                ps.setString(3, entity.getPhoneNumber());
                ps.setInt(4, entity.Id);
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
    public Customer getById(int Id) {
        Customer customer = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                ps.setInt(1, Id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    customer = new Customer(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phoneNumber")
                    );
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }
        }
        return customer;
    }

    @Override
    public Customer getById(String id) {
        Customer customer = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    customer = new Customer(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phoneNumber")
                    );
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }
        }
        return customer;
    }

}
