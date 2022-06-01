/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Repositories;

/**
 *
 * @author macpe
 */
public class ReviewRepository extends RepositoryBase<Review> {
    private static String tableName = "review";
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rs;
    private int rowsAffected;

    public ReviewRepository() {
        super(tableName);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/carolsboutique";
        try {
            con = DriverManager.getConnection(url,"root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Review review) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+" (comment,rating,saleId) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,review.getComment());
            ps.setInt(2,review.getRating());
            ps.setString(3,review.getSaleId());

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                review.Id = keys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Review Repository class in the add(Review review) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean update(Review review) {
        try {
            ps = con.prepareStatement("INSERT INTO "+tableName+"(comment,rating,saleId) VALUES (?,?,?) WHERE id = ?",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,review.getComment());
            ps.setInt(2,review.getRating());
            ps.setString(3,review.getSaleId());
            ps.setInt(4,review.Id);

            rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if(keys.next()){
                review.Id = keys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Review Repository class at the update(Review review) method.");
            throw new RuntimeException(e);
        }
        return rowsAffected == 1;
    }

    @Override
    public Review getById(int Id) {
        Review review= null;
        try {
            ps = con.prepareStatement("SELECT * FROM "+tableName+" WHERE = ?");
            ps.setInt(1,Id);
            rs = ps.executeQuery();

            if(rs.next()){
                review = new Review(
                        rs.getString("comment"),
                        rs.getInt("rating"),
                        rs.getString("saleId")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQLException thrown in the Review Repository class at the getById(int Id) method");
            throw new RuntimeException(e);
        }

        return review;
    }

    @Override
    public Review getById(String id) {
        return null;
    }

    @Override
    protected List<Review> executeQuery(String statement) {
        List<Review> reviews = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    Review review = new Review(
                            rs.getString("comment"),
                            rs.getInt("rating"),
                            rs.getString("saleId")
                    );
                    reviews.add(review);
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

        return reviews;
    }
}

