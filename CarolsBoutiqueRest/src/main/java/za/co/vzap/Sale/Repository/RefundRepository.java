package za.co.vzap.Sale.Repository;

import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.POS.Model.Refund;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;
import za.co.vzap.POS.Model.RefundStatusEnum;

public class RefundRepository extends RepositoryBase<Refund> {
    private static String tableName = "refund";
    
    public RefundRepository() {
        super(tableName, null);
    }

    @Override
    public int add(Refund refund) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(saleId, date, status) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, refund.getSaleId());
                ps.setTimestamp(2, refund.getDate());
                ps.setInt(3, refund.getStatus().getValue());
                
                rowsAffected = ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    refund.Id = keys.getInt(1);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return refund.Id;
    }

    @Override
    public boolean update(Refund refund) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set saleId = ?, date = ?, status = ? where id = ?");
                ps.setString(1, refund.getSaleId());
                ps.setTimestamp(2, refund.getDate());
                ps.setInt(3, refund.getStatus().getValue());
                ps.setInt(4, refund.Id);

                rowsAffected = ps.executeUpdate();

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return rowsAffected == 1;
    }

    @Override
    public Refund getById(int Id) {
        Refund refund = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = '" + Id + "'");

                rs = ps.executeQuery();

                if(rs.next()) {
                    refund = new Refund(
                            rs.getString("saleId"),
                            rs.getTimestamp("date"),
                            RefundStatusEnum.valueOf(rs.getInt("status"))
                    );

                    refund.Id = rs.getInt("id");
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return refund;
    }

    @Override
    public Refund getById(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<Refund> executeQuery(String statement) {
         List<Refund> refunds = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");

                    Refund refund = new Refund(
                            rs.getString("saleId"),
                            rs.getTimestamp("date"),
                            RefundStatusEnum.valueOf(rs.getInt("status"))
                    );

                    refund.Id = id;
                    refunds.add(refund);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return refunds;
    }

    @Override
    public String add2(Refund arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
