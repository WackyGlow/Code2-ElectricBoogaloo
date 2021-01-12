package PMC.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

    public class DatabaseConnector {


        private static final String PROP_FILE = "src/pmc/dal/database.settings";
        private SQLServerDataSource ds;

        /**
         * Constructor for the database connector.
         * @throws IOException
         */
        public DatabaseConnector() throws IOException
        {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(PROP_FILE));
            ds = new SQLServerDataSource();
            ds.setServerName(databaseProperties.getProperty("Server"));
            ds.setDatabaseName(databaseProperties.getProperty("Database"));
            ds.setUser(databaseProperties.getProperty("User"));
            ds.setPassword(databaseProperties.getProperty("Password"));
        }

        /**
         * Connects the application to our SQL database.
         * @return
         * @throws SQLServerException
         */
        public Connection getConnection() throws SQLServerException
        {
            return ds.getConnection();
        }
    }
