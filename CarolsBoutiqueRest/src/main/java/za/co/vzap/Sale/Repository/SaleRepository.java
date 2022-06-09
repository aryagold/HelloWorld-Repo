package za.co.vzap.Sale.Repository;

import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Sale.Model.Sale;
import za.co.vzap.Sale.Model.SaleStatusEnum;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository extends RepositoryBase<Sale> {
    private static String tableName = "sale";
    private static final String idPrefix = "SL";
    
    public SaleRepository() {
        super(tableName, idPrefix);
    }
    
    @Override
    public String add2(Sale sale) {
        String id = getNextCode();
        
        sale.setSaleId(id);
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(id, userId, customerEmail, date, paymentId, status) values(?, ?, ?, ?, ?, ?)");
                ps.setString(1, id);
                ps.setString(2, sale.getUserId());
                ps.setString(3, sale.getCustomerEmail());
                ps.setTimestamp(4, sale.getDate());
                ps.setObject(5, sale.getPaymentId());
                ps.setInt(6, sale.getStatus().getValue());
                
               
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

        return sale.saleId;
    }

    @Override
    public boolean update(Sale sale) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set userId = ?, customerEmail = ?, date = ?, paymentId = ?, status = ? where id = ?");
                ps.setString(1, sale.getUserId());
                ps.setString(2, sale.getCustomerEmail());
                ps.setTimestamp(3, sale.getDate());
                ps.setInt(4, sale.getPaymentId());
                ps.setInt(5, sale.getStatus().getValue());
                ps.setString(6, sale.saleId);
               
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
                ps = con.prepareStatement("select * from " + tableName + " where id = '" + Id + "'");

                rs = ps.executeQuery();

                if(rs.next()) {
                    sale = new Sale(
                            rs.getString("userId"),
                            rs.getString("customerEmail"),
                            rs.getTimestamp("date"),
                            rs.getInt("paymentId"),
                            SaleStatusEnum.valueOf(rs.getInt("status"))
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
                            rs.getString("customerEmail"),
                            rs.getTimestamp("date"),
                            rs.getInt("paymentId"),
                            SaleStatusEnum.valueOf(rs.getInt("status"))
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

    @Override
    public int add(Sale arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
