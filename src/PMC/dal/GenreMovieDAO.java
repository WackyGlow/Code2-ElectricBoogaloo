package PMC.dal;

import PMC.be.Genre;
import PMC.be.GenreMovie;
import PMC.be.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void saveLink(Genre g, Movie m) throws SQLException {
        int movieId = m.getId();
        int genreId = g.getId();
        String sql = "INSERT INTO GenreMovie (genreId,movieId) VALUES(?,?);";
        // preparedStatement = connection.prepareStatement(query);
        // preparedStatement.setInt(1,g.getId());
        // preparedStatement.setInt(2,m.getId());
        // preparedStatement.executeUpdate();
    }

}
