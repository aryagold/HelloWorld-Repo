package za.co.vzap.Customer.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Customer.Model.Customer;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class CustomerRepository extends RepositoryBase<Customer> {
    private static String tableName = "customer";

    public CustomerRepository() {
        super(tableName, null);
    }

    @Override
    protected List<Customer> executeQuery(String statement) {
        List<Customer> customers = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement(statement);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    
                    Customer customer = new Customer(
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
    public boolean add(Customer customer) {
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(email, phoneNumber) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customer.getEmail());
                ps.setString(2, customer.getPhoneNumber());
                
                rowsAffected = ps.executeUpdate();
                
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    customer.Id = keys.getInt(1);
                }
                
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
                ps = con.prepareStatement("UPDATE " + tableName + " SET email = ?, phoneNumber = ? where id = ?");
                ps.setString(1, entity.getEmail());
                ps.setString(2, entity.getPhoneNumber());
                ps.setInt(3, entity.Id);
                
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
                            rs.getString("email"),
                            rs.getString("phoneNumber")
                    );
                    
                    customer.Id = rs.getInt("id");
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
        return null;
    }

}
