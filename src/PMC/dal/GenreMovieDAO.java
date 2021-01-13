package PMC.dal;

import PMC.be.GenreMovie;

import java.io.IOException;
import java.sql.Connection;
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


}
