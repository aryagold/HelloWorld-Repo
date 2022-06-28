package za.co.vzap.Customer.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.co.vzap.Customer.Model.Review;
import za.co.vzap.Interface.Repository.RepositoryBase;

/**
 *
 * @author macpe
 */
public class ReviewRepository extends RepositoryBase<Review> {
    private static String tableName = "review";

    /**
     *
     */
    public ReviewRepository() {
        super(tableName, null);
    }

    /**
     *
     * @param review
     * @return
     */
    @Override
    public int add(Review review) {
        try {
            ps = con.prepareStatement("INSERT INTO " + tableName + "(comment, rating, branchId, date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,review.getComment());
            ps.setInt(2,review.getRating());
            ps.setString(3,review.getBranchId());
            ps.setTimestamp(4, review.getDate());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                review.Id = keys.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Review Repository class in the add(Review review) method.");
            throw new RuntimeException(e);
        }finally{
            
            closeStreams(rs, ps);
            
        }
        
        return review.Id;
    }

    /**
     *
     * @param review
     * @return
     */
    @Override
    public boolean update(Review review) {
        try {
            ps = con.prepareStatement("Update " + tableName + " set comment = ?, rating = ?, branchId = ?, date = ? WHERE id = ?");
            ps.setString(1,review.getComment());
            ps.setInt(2,review.getRating());
            ps.setString(3,review.getBranchId());
            ps.setTimestamp(4, review.getDate());
            ps.setInt(5,review.Id);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Review Repository class at the update(Review review) method.");
            throw new RuntimeException(e);
        }finally{
            
            closeStreams(rs, ps);
            
        }
        
        return rowsAffected == 1;
    }

    /**
     *
     * @param Id
     * @return
     */
    @Override
    public Review getById(int Id) {
        Review review= null;
        
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
            ps.setInt(1,Id);
            rs = ps.executeQuery();

            if(rs.next()){
                review = new Review(
                        rs.getString("comment"),
                        rs.getInt("rating"),
                        rs.getString("branchId"),
                        rs.getTimestamp("date")
                );
                
                review.Id = rs.getInt("id");
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Review Repository class at the getById(int Id) method");
            throw new RuntimeException(e);
        }finally{
            
            closeStreams(rs, ps);
            
        }
        

        return review;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Review getById(String id) {
       throw new UnsupportedOperationException("Not supported yet");
    }

    /**
     *
     * @param statement
     * @return
     */
    @Override
    protected List<Review> executeQuery(String statement) {
        List<Review> reviews = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    
                    Review review = new Review(
                            rs.getString("comment"),
                            rs.getInt("rating"),
                            rs.getString("branchId"),
                            rs.getTimestamp("date")
                    );
                    
                    review.Id = id;
                    reviews.add(review);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return reviews;
    }

    @Override
    public String add2(Review arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

