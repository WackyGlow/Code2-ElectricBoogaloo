package PMC.dal;

import PMC.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private final JDBCConnectionPool connectionPool;

    public MovieDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<Movie> getAllMovies() throws IOException {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT * FROM Movie;";
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Title");
                    Float rating = resultSet.getFloat("Rating");
                    Float userRating = resultSet.getFloat("Userrating");
                    String filepath = resultSet.getString("Filepath");
                    String lastView = resultSet.getString("Lastview");
                    Movie movie = new Movie(name,rating,userRating,filepath,lastView,id);
                    allMovies.add(movie);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMovies;
    }

    public Movie createMovie(String title, float imdbRating, float rating, String filepath, String lastWatched) throws SQLException {
        String sql = "INSERT INTO Song (title, imdbrating, rating, filepath, lastviewed) VALUES(?,?,?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, title);
            st.setFloat(2,imdbRating);
            st.setFloat(3,rating);
            st.setString(4,filepath);
            st.setString(5,lastWatched);
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }
            Movie movie = new Movie(title,imdbRating,rating,filepath,lastWatched,id);
            return movie;
        } catch (SQLException ex) {
            throw new SQLException("Could not create movie", ex);
        } finally {
            connectionPool.checkIn(con);
        }
    }
}
