package co.vzap.Sale.Repository;

import co.vzap.Sale.Model.Sale;
import co.vzap.Sale.Model.SaleStatusEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository extends RepositoryBase<Sale> {
    private static String tableName = "sale";
    private static final String idPrefix = "S";
    
    public SaleRepository() {
        super(tableName);
    }
    
    @Override
    public boolean add(Sale sale) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(saleId, userId, customerId, date, paymentId, status, branchId) values(?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, getNextCode());
                ps.setString(2, sale.getUserId());
                ps.setInt(3, sale.getCustomerId());
                ps.setTimestamp(4, sale.getDate());
                ps.setInt(5, sale.getPaymentId());
                ps.setInt(6, sale.getStatus().getValue());
                ps.setString(7, sale.getBranchId());
               
                rowsAffected = ps.executeUpdate();

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
    public boolean update(Sale sale) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set saleId = ?, userId = ?, customerId = ?, date = ?, paymentId = ?, status = ?, branchId = ?");
                ps.setString(1, sale.getSaleId());
                ps.setString(2, sale.getUserId());
                ps.setInt(3, sale.getCustomerId());
                ps.setTimestamp(4, sale.getDate());
                ps.setInt(5, sale.getPaymentId());
                ps.setInt(6, sale.getStatus().getValue());
                ps.setString(7, sale.getBranchId());
               
                rowsAffected = ps.executeUpdate();

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
    public Sale getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sale getById(String Id) {
        Sale sale = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    sale = new Sale(
                            rs.getString("userId"),
                            rs.getInt("customerId"),
                            rs.getTimestamp("date"),
                            rs.getInt("paymentId"),
                            rs.getString("branchId"),
                            SaleStatusEnum.ofStatusCode(rs.getInt("status"))
                    );
                      
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

        return sale;
    }
    
    @Override
    protected List<Sale> executeQuery(String statement) {
        List<Sale> sales = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    Sale sale = new Sale(
                            rs.getString("userId"),
                            rs.getInt("customerId"),
                            rs.getTimestamp("date"),
                            rs.getInt("paymentId"),
                            rs.getString("branchId"),
                            SaleStatusEnum.ofStatusCode(rs.getInt("status"))
                    );

                    sales.add(sale);
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

        return sales;
    }
}
