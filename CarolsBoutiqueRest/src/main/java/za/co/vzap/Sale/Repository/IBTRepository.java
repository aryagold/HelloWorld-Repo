package za.co.vzap.Sale.Repository;

import za.co.vzap.Interface.Repository.RepositoryBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Sale.Model.IBT;
import za.co.vzap.Sale.Model.IBTStatusEnum;

public class IBTRepository extends RepositoryBase<IBT> {
    private static String tableName = "ibt";
    
    public IBTRepository() {
        super(tableName, null);
    }

    @Override
    public int add(IBT ibt) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(inventoryIdFrom, branchIdTo, quantity, customerPhoneNumber, emailAddress, status) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, ibt.getInventoryIdFrom());
                ps.setString(2, ibt.getBranchIdTo());
                ps.setInt(3, ibt.getQuantity());
                ps.setString(4, ibt.getPhoneNumber());
                ps.setString(5, ibt.getEmailAddress());
                ps.setInt(6, ibt.getStatus().getValue());
               
                rowsAffected = ps.executeUpdate();
                
                ResultSet keys = ps.getGeneratedKeys();

                if(keys.next()) {
                    ibt.Id = keys.getInt(1);
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

        return ibt.Id;
    }

    @Override
    public boolean update(IBT ibt) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set inventoryIdFrom = ?, branchIdTo = ?, quantity = ?, customerPhoneNumber = ?, emailAddress = ?, status = ? where id = ?");
                ps.setInt(1, ibt.getInventoryIdFrom());
                ps.setString(2, ibt.getBranchIdTo());
                ps.setInt(3, ibt.getQuantity());
                ps.setString(4, ibt.getPhoneNumber());
                ps.setString(5, ibt.getEmailAddress());
                ps.setInt(6, ibt.getStatus().getValue());
                ps.setInt(7, ibt.Id);
                
                rowsAffected = ps.executeUpdate();

            } catch(SQLException e) {
                System.out.println("Exception in update in ibtrepository");
                     
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
    public IBT getById(int Id) {
        IBT ibt = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = '" + Id + "'");

                rs = ps.executeQuery();

                if(rs.next()) {
                    ibt = new IBT(
                            rs.getInt("inventoryIdFrom"),
                            rs.getString("branchIdTo"),
                            rs.getInt("quantity"),
                            rs.getString("customerPhoneNumber"),
                            rs.getString("emailAddress"),
                            IBTStatusEnum.valueOf(rs.getInt("status"))
                    );

                    ibt.Id = rs.getInt("id");
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

        return ibt;
    }

    @Override
    public IBT getById(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<IBT> executeQuery(String statement) {
        List<IBT> ibts = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");

                    IBT ibt = new IBT(
                            rs.getInt("inventoryIdFrom"),
                            rs.getString("branchIdTo"),
                            rs.getInt("quantity"),
                            rs.getString("customerPhoneNumber"),
                            rs.getString("emailAddress"),
                            IBTStatusEnum.valueOf(rs.getInt("status"))
                    );

                    ibt.Id = id;
                    ibts.add(ibt);
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

        return ibts;
    }

    @Override
    public String add2(IBT arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
