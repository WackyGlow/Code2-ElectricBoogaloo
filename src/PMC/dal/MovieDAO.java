package PMC.dal;

import java.io.IOException;

public class MovieDAO {

    private final JDBCConnectionPool connectionPool;

    public MovieDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }
}
