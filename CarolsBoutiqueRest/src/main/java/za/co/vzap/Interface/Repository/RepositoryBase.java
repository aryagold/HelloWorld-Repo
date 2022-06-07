package za.co.vzap.Interface.Repository;

import za.co.vzap.Interface.Repository.IRepository;
import java.sql.*;
import java.util.List;

public abstract class RepositoryBase<IEntity> implements IRepository<IEntity> {
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected int rowsAffected;
    
    protected String tableName;
    protected String idPrefix;

    public RepositoryBase(String tableName, String idPrefix) {
        this.tableName = tableName;
        this.idPrefix = idPrefix;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://127.0.0.1:3306/carolsboutique?useSSL=false";
        try {
            this.con = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(int id) {
        if (con != null){
            try {
                ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE id = " + id);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        
        return rowsAffected==1;
    }

    @Override
    public boolean deleteById(String id) {
        if (con != null){
            try {
                ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE id = " + id);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        
        return rowsAffected==1;
    }

    @Override
    public List<IEntity> getWhere(String field, String value) {
        
        String statement = "SELECT * from " + tableName + " WHERE "+ field + " = " + value;
        
        return executeQuery(statement);
    }

    @Override
    public List<IEntity> getWhere(String field, int value) {
        
        String statement = "SELECT * from " + tableName + " WHERE "+ field + " = " + value;
        
        return executeQuery(statement);
    }

    @Override
    public List<IEntity> getAll() {
        
        String statement = "SELECT * FROM "+ tableName;
        
        return executeQuery(statement);
    }
    
    protected String getNextCode() {
        String nextCode = "";
        String statement = "Select max(id) from " + tableName;
        
        int i = 0;
        
        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    String lastId = rs.getString("id");
                    String lastNumber = lastId.substring(idPrefix.length());
                    
                    i = Integer.parseInt(lastNumber);
                }
                
                nextCode = idPrefix + String.format("%04d", i++);

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
        
        return nextCode;
    }
    
    public void closeStreams(ResultSet rs, PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract List<IEntity> executeQuery(String statement);
}
