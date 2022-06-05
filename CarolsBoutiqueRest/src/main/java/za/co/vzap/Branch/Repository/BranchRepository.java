package za.co.vzap.Branch.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class BranchRepository extends RepositoryBase<Branch> {
    private static String tableName = "branch";
    private static final String idPrefix = "BR";
    
    public BranchRepository() {
        super(tableName, idPrefix);
    }

    @Override
    public String add2(Branch branch) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Insert Into " + tableName + "(id, name, monthlyTarget, dailyTarget) values(?, ?, ?, ?)");
                ps.setString(1, getNextCode());
                ps.setString(2, branch.getName());
                ps.setDouble(3, branch.getMonthlyTarget());
                ps.setDouble(4, branch.getDailyTarget());
               
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

        return branch.branchId;
    }

    @Override
    public boolean update(Branch branch) {
        if(con != null) {
            try {
                ps = con.prepareStatement("Update " + tableName + " set name = ?, monthlyTarget = ?, dailyTarget = ? where id = ?");
                
                ps.setString(1, branch.getName());
                ps.setDouble(2, branch.getMonthlyTarget());
                ps.setDouble(3, branch.getDailyTarget());
                ps.setString(4, branch.branchId);
               
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
    public Branch getById(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Branch getById(String Id) {
        Branch branch = null;

        if(con != null) {
            try {
                ps = con.prepareStatement("select * from " + tableName + " where id = " + Id);

                rs = ps.executeQuery();

                if(rs.next()) {
                    branch = new Branch(
                            rs.getString("name"),
                            rs.getDouble("monthlyTarget"),
                            rs.getDouble("dailyTarget")
                    );
                    
                    branch.branchId = rs.getString("id");
                      
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

        return branch;
    }
    
    @Override
    protected List<Branch> executeQuery(String statement) {
        List<Branch> branches = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    Branch branch = new Branch(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getDouble("monthlyTarget"),
                            rs.getDouble("dailyTarget")
                    );

                    branches.add(branch);
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

        return branches;
    }

    @Override
    public int add(Branch arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
