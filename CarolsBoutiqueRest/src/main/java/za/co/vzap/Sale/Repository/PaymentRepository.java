package za.co.vzap.Sale.Repository;

import za.co.vzap.Customer.Repository.CustomerRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Sale.Model.Payment;
import za.co.vzap.Sale.Model.PaymentTypeEnum;

public class PaymentRepository extends RepositoryBase<Payment> {

    private static String tableName = "payment";

    public PaymentRepository() {
        super(tableName, null);
    }

    @Override
    protected List<Payment> executeQuery(String statement) {
        List<Payment> payments = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement(statement);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    
                    Payment pay = new Payment(
                            PaymentTypeEnum.ofStatusCode(rs.getInt("type")),
                            rs.getString("cardNumber"),
                            rs.getBoolean("approved")
                    );
                    
                    pay.Id = id;
                    payments.add(pay);

                }

            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }

        }
        return payments;
    }

    @Override
    public boolean add(Payment entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO " + tableName + "(type, cardNumber, approved) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, entity.getType().getValue());
                ps.setString(2, entity.getCardNumber());
                ps.setBoolean(3, entity.isApproved());
                
                rowsAffected = ps.executeUpdate();
                
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    entity.Id = keys.getInt(1);
                }
                
            } catch (SQLException ex) {

            } finally {
                closeStreams(rs, ps);
            }
        }
        
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Payment entity) {
        if (con != null) {
            try {
                ps = con.prepareStatement("UPDATE " + tableName + " SET type = ?, cardNumber = ?, approved = ? where id = ?");
                ps.setInt(1, entity.getType().getValue());
                ps.setString(2, entity.getCardNumber());
                ps.setBoolean(3, entity.isApproved());
                ps.setInt(4, entity.Id);
                
                rowsAffected = ps.executeUpdate();

            } catch (SQLException var11) {
                var11.printStackTrace();
            } finally {
                closeStreams(rs, ps);
            }
        }

        return rowsAffected == 1;
    }

    @Override
    public Payment getById(int Id) {
        Payment pay = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                ps.setInt(1, Id);
                
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    pay = new Payment(
                            PaymentTypeEnum.ofStatusCode(rs.getInt("type")),
                            rs.getString("cardNumber"),
                            rs.getBoolean("approved")
                    );
                    
                    pay.Id = rs.getInt("id");

                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }
        }
        
        return pay;
    }

    @Override
    public Payment getById(String id) {
        return null;
    }

}
