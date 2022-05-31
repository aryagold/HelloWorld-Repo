package co.vzap.Sale.Repository;

import co.vzap.Sale.Model.SaleLineItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class SaleLineItemRepository extends RepositoryBase<SaleLineItem> {
    private static String tableName = "salelineitem";
    
    public SaleLineItemRepository() {
        super(tableName);
    }

    @Override
    public boolean add(SaleLineItem saleLineItem) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(productId, quantity) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, saleLineItem.getProductId());
                ps.setInt(2, saleLineItem.getQuantity());
               

                rowsAffected = ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    saleLineItem.Id = keys.getInt(1);
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
    public boolean update(SaleLineItem saleLineItem) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set productId = ?, quantity = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, saleLineItem.getProductId());
                ps.setInt(2, saleLineItem.getQuantity());
               

                rowsAffected = ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    saleLineItem.Id = keys.getInt(1);
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
    public SaleLineItem getById(int Id) {
        SaleLineItem saleLineItem = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    saleLineItem = new SaleLineItem(
                            rs.getString("productId"),
                            rs.getInt("quantity")
                    );

                    saleLineItem.Id = rs.getInt("id");
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

        return saleLineItem;
    }

    @Override
    public SaleLineItem getById(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<SaleLineItem> executeQuery(String statement) {
        List<SaleLineItem> saleLineItems = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");

                    SaleLineItem saleLineItem = new SaleLineItem(
                            rs.getString("productId"),
                            rs.getInt("quantity")
                    );

                    saleLineItem.Id = id;
                    saleLineItems.add(saleLineItem);
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

        return saleLineItems;
    }
}
