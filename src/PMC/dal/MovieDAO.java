package PMC.dal;

import PMC.be.Genre;
import PMC.be.Movie;
import PMC.bll.MovieManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private final JDBCConnectionPool connectionPool;

    /**
     * Implements a connection pool.
     * @throws IOException
     */
    public MovieDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Creates a new arraylist for the movies.
     * @return allMovies
     * @throws IOException
     */
    public List<Movie> getAllMovies() throws IOException {
        ArrayList<Movie> allMovies = new ArrayList<>(){
        };

        try (Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT * FROM Movie;";
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
                    allMovies.add(movie);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMovies;
    }

    /**
     * Makes it possible to create a new movie, and sets the parameters of whats considered a "legal" movie.
     * @param title
     * @param rating
     * @param userrating
     * @param filepath
     * @param lastview
     * @return movie
     * @throws SQLException
     */
    public Movie createMovie(String title, int rating, int userrating, String filepath, String lastview) throws SQLException {
        String sql = "INSERT INTO Movie (title, rating, userrating, filepath, lastview) VALUES(?,?,?,?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, title);
            st.setInt(2,rating);
            st.setInt(3,userrating);
            st.setString(4,filepath);
            st.setString(5,lastview);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }
            Movie movie = new Movie(title,rating,userrating,filepath,lastview,id);

            MovieManager movieManager = new MovieManager();
            List<Genre> newMovieSelectedGenres = movieManager.getNewMovieSelectedGenres();
            for(Genre genre : newMovieSelectedGenres){
                saveLink(genre,movie);
            }

            return movie;
        } catch (SQLException | IOException ex) {
            throw new SQLException("Could not create movie", ex);
        } finally {
            connectionPool.checkIn(con);
        }
    }

    /**
     * Makes it possible to delete the selected movie.
     * @param selectedMovie
     * @throws SQLException
     */
    public void deleteMovie(Movie selectedMovie) throws SQLException {
        String name = selectedMovie.getName();
        try (Connection con = connectionPool.checkOut()) {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            if (statement.execute(sql)) {
                PreparedStatement pSql = con.prepareStatement("DELETE FROM Movie WHERE Title=?");
                pSql.setString(1,name);
                pSql.execute();
            }
        }
    }

    /**
     * Takes the given parameters and uses them to edit a selected movie.
     * @param selectedMovie
     * @param title
     * @param rating
     * @param userrating
     * @param filepath
     * @param lastview
     * @throws SQLException
     */
    public void updateMovie(Movie selectedMovie, String title, int rating, int userrating, String filepath, String lastview) throws SQLException {
        //String sql = "INSERT INTO Movie (title, rating, userrating, filepath, lastview) VALUES(?,?,?,?,?);";
        int moveId;
        moveId = selectedMovie.getId();
        String sql = "UPDATE MOVIE SET title = ?, rating = ?, userrating = ?, filepath = ?, lastview = ? WHERE Id =" + moveId + ";";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1,title);
            st.setInt(2,rating);
            st.setInt(3,userrating);
            st.setString(4,filepath);
            st.setString(5,lastview);
            st.executeUpdate();

            MovieManager movieManager = new MovieManager();
            List<Genre> editMovieSelectedGenres = movieManager.getEditMovieSelectedGenres();
            for(Genre genre : editMovieSelectedGenres){
                updateLink(genre,selectedMovie);
            }
        } catch (SQLException | IOException ex) {
            throw new SQLException("Could not update movie", ex);
        } finally {
            connectionPool.checkIn(con);
        }
    }

    /**
     * Takes a movie and a genre, and links them together in a junction table in the database.
     * @param g
     * @param m
     * @throws SQLException
     */
    public void saveLink(Genre g, Movie m) throws SQLException {
        String sql = "INSERT INTO GenreMovie (playlistId,songId) VALUES(?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, g.getId());
            preparedStatement.setInt(2, m.getId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Updates the link between the movie and the genre in the junction table in the database.
     * @param g
     * @param m
     * @throws SQLException
     */
    public void updateLink(Genre g, Movie m) throws SQLException {
        String sql = "UPDATE GenreMovie SET playlistId=? WHERE songId= " + m.getId() + ";";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, g.getId());
            preparedStatement.executeUpdate();
        }
    }
}
