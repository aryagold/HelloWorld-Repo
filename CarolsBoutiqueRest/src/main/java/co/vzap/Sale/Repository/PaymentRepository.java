/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.vzap.Sale.Repository;

import co.vzap.Customer.Repository.CustomerRepository;
import co.vzap.Sale.Model.Payment;
import co.vzap.Sale.Model.PaymentTypeEnum;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentRepository extends RepositoryBase<Payment> {

    private static String tableName = "payment";

    public PaymentRepository() {
        super(tableName);
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
                ps = con.prepareStatement("INSERT INTO " + tableName + " (type, cardNumber, approved) values(?, ?, ?)");
                ps.setInt(1, entity.getType().getValue());
                ps.setString(2, entity.getCardNumber());
                ps.setBoolean(3, entity.isApproved());
                rowsAffected = ps.executeUpdate();
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
        Payment pay = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    pay = new Payment(
                            PaymentTypeEnum.ofStatusCode(rs.getInt("type")),
                            rs.getString("cardNumber"),
                            rs.getBoolean("approved")
                    );

                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStreams(rs, ps);
            }
        }
        return pay;
    }

}
