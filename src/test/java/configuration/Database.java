package configuration;

import org.h2.tools.RunScript;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private final DataSource dataSource;

    private Database() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("su");
        dataSource.setPassword("");
        try {
            loadTestData(dataSource.getConnection());
        } catch (Exception exception) {
            System.out.println("Error loading test data: " + exception.getMessage());
        }
        this.dataSource = dataSource;
    }

    public static DataSource getDataSource() {
        if (instance == null) {
            instance = new Database();
        }
        return instance.dataSource;
    }

    private static void loadTestData(Connection connection) throws IOException, SQLException {
        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }
}
