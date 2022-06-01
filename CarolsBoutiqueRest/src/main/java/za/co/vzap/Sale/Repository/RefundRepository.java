package za.co.vzap.Sale.Repository;

import za.co.vzap.Sale.Model.Refund;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class RefundRepository extends RepositoryBase<Refund> {
    private static String tableName = "refund";
    
    public RefundRepository() {
        super(tableName);
    }

    @Override
    public boolean add(Refund refund) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(saleId, date) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, refund.getSaleId());
                ps.setTimestamp(2, refund.getDate());
               

                rowsAffected = ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    refund.Id = keys.getInt(1);
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

        return rowsAffected == 1;
    }

    @Override
    public boolean update(Refund refund) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set saleId = ?, date = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, refund.getSaleId());
                ps.setTimestamp(2, refund.getDate());
               

                rowsAffected = ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    refund.Id = keys.getInt(1);
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

        return rowsAffected == 1;
    }

    @Override
    public Refund getById(int Id) {
        Refund refund = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    refund = new Refund(
                            rs.getString("saleId"),
                            rs.getTimestamp("date")
                    );

                    refund.Id = rs.getInt("id");
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
                            rs.getTimestamp("date")
                    );

                    refund.Id = id;
                    refunds.add(refund);
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

        return refunds;
    }
    
}
