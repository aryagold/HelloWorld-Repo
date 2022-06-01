package za.co.vzap.Sale.Repository;

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
    public boolean add(IBT ibt) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(branchIdFrom, branchIdTo, productId, quantity, email, status) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ibt.getBranchIdFrom());
                ps.setString(2, ibt.getBranchIdTo());
                ps.setString(3, ibt.getProductId());
                ps.setInt(4, ibt.getQuantity());
                ps.setString(5, ibt.getEmail());
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

        return rowsAffected == 1;
    }

    @Override
    public boolean update(IBT ibt) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set branchIdFrom = ?, branchIdTo = ?, productId = ?, quantity = ?, email = ?, status = ? where id = ?");
                ps.setString(1, ibt.getBranchIdFrom());
                ps.setString(2, ibt.getBranchIdTo());
                ps.setString(3, ibt.getProductId());
                ps.setInt(4, ibt.getQuantity());
                ps.setString(5, ibt.getEmail());
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
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    ibt = new IBT(
                            rs.getString("branchIdFrom"),
                            rs.getString("branchIdTo"),
                            rs.getString("productId"),
                            rs.getInt("quantity"),
                            rs.getString("email"),
                            IBTStatusEnum.ofStatusCode(rs.getInt("status"))
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
                            rs.getString("branchIdFrom"),
                            rs.getString("branchIdTo"),
                            rs.getString("productId"),
                            rs.getInt("quantity"),
                            rs.getString("email"),
                            IBTStatusEnum.ofStatusCode(rs.getInt("status"))
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
    
}
