package PMC.dal;

import PMC.be.Genre;
import PMC.be.GenreMovie;
import PMC.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreMovieDAO {
    private final JDBCConnectionPool connectionPool;

    /**
     * The constructor implements the connectionpool for use in its methods,
     * which will be handling information being sent to the Database
     * @throws IOException
     */
    public GenreMovieDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * The methods takes a genre object and extracts and ID which is used to get all the movies
     * from a specific genre.
     * @param selectedGenre
     * @return
     * @throws IOException
     */
    public List<Movie> getAllMoviesFromGenre(Genre selectedGenre) throws IOException {
        ArrayList<Movie> allMovieGenres = new ArrayList<>(){

        };

        try (Connection connection = connectionPool.checkOut()) {
            int genreId = selectedGenre.getId();
            String sql = "SELECT * FROM Movie  INNER JOIN GenreMovie ON Movie.id = GenreMovie.SongId WHERE PlaylistId = " + genreId +";";
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Title");
                    int rating = resultSet.getInt("Rating");
                    int userRating = resultSet.getInt("Userrating");
                    String filepath = resultSet.getString("Filepath");
                    String lastView = resultSet.getString("Lastview");
                    Movie movie = new Movie(name,rating,userRating,filepath,lastView,id);
                    allMovieGenres.add(movie);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMovieGenres;
    }


    public void saveLink(Genre g, Movie m) throws SQLException {
        String sql = "INSERT INTO GenreMovie (genreId,movieId) VALUES(?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, g.getId());
            preparedStatement.setInt(2, m.getId());
            preparedStatement.executeUpdate();
        }
    }

}
