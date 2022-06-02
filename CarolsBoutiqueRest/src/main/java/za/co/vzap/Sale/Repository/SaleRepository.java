package za.co.vzap.Sale.Repository;

import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository extends RepositoryBase<Sale> {
    private static String tableName = "sale";
    private static final String idPrefix = "SL";
    
    public SaleRepository() {
        super(tableName, idPrefix);
    }
    
    @Override
    public boolean add(Sale sale) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(id, userId, email, date, paymentId, status, branchId) values(?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, getNextCode());
                ps.setString(2, sale.getUserId());
                ps.setString(3, sale.getEmail());
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
                ps = con.prepareStatement("Update " + tableName + " set userId = ?, email = ?, date = ?, paymentId = ?, status = ?, branchId = ? where id = ?");
                ps.setString(1, sale.getUserId());
                ps.setString(2, sale.getEmail());
                ps.setTimestamp(3, sale.getDate());
                ps.setInt(4, sale.getPaymentId());
                ps.setInt(5, sale.getStatus().getValue());
                ps.setString(6, sale.getBranchId());
                ps.setString(7, sale.saleId);
               
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
                            rs.getString("email"),
                            rs.getTimestamp("date"),
                            rs.getInt("paymentId"),
                            rs.getString("branchId"),
                            SaleStatusEnum.ofStatusCode(rs.getInt("status"))
                    );
                    
                    sale.saleId = rs.getString("id");
                      
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
                            rs.getString("id"),
                            rs.getString("userId"),
                            rs.getString("email"),
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
