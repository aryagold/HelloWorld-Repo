package za.co.vzap.Sale.Repository;

import za.co.vzap.Interface.Repository.RepositoryBase;
import za.co.vzap.Sale.Model.RefundItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RefundItemRepository extends RepositoryBase<RefundItem> {
    private static String tableName = "refund";
    
    public RefundItemRepository() {
        super(tableName, null);
    }

    @Override
    public int add(RefundItem refundItem) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(productId, quantity) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, refundItem.getProductId());
                ps.setInt(2, refundItem.getQuantity());
               
                rowsAffected = ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    refundItem.Id = keys.getInt(1);
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

        return refundItem.Id;
    }

    @Override
    public boolean update(RefundItem refundItem) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set productId = ?, quantity = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, refundItem.getProductId());
                ps.setInt(2, refundItem.getQuantity());
                ps.setInt(3, refundItem.Id);

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
    public RefundItem getById(int Id) {
        RefundItem refundItem = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    refundItem = new RefundItem(
                            rs.getString("productId"),
                            rs.getInt("quantity")
                    );

                    refundItem.Id = rs.getInt("id");
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

        return refundItem;
    }

    @Override
    public RefundItem getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<RefundItem> executeQuery(String statement) {
        List<RefundItem> refundItems = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");

                    RefundItem refundItem = new RefundItem(
                            rs.getString("productId"),
                            rs.getInt("quantity")
                    );

                    refundItem.Id = id;
                    refundItems.add(refundItem);
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

        return refundItems;
    }

    @Override
    public String add2(RefundItem arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
