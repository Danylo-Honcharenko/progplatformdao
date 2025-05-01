package configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectTest {

    @Test
    public void connectionTest() {
        assertNotNull(Database.getDataSource());
    }
}
