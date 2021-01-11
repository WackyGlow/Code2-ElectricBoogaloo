package PMC.dal;

import PMC.be.Genre;
import PMC.be.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private final JDBCConnectionPool connectionPool;

    public GenreDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<Genre> getAllGenres() throws IOException {
        ArrayList<Genre> allGenres = new ArrayList<>();

        try (Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT * FROM Genre;";
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name");
                    Genre genre = new Genre(id,name);
                    allGenres.add(genre);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allGenres;
    }

    public Genre createGenre(String name) throws SQLException {
        String sql = "INSERT INTO Genre (name) VALUES(?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, name);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }
            Genre genre = new Genre(id, name);
            return genre;
        } catch (SQLException ex) {
            throw new SQLException("Could not create genre", ex);
        } finally {
            connectionPool.checkIn(con);
        }
    }

    public void deleteGenre(Genre selectedGenre) throws SQLException {
        String name = selectedGenre.getName();
        try (Connection con = connectionPool.checkOut()) {
            String sql = "SELECT * FROM Genre;";
            Statement statement = con.createStatement();
            if (statement.execute(sql)) {
                PreparedStatement pSql = con.prepareStatement("DELETE FROM Genre WHERE Name=?");
                pSql.setString(1,name);
                pSql.execute();
            }
        }
    }
}
