package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.AuthDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.AuthDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Auth;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthDaoImplTest {

    private final AuthDaoI authDao = new AuthDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createAuth() {
        Auth auth = Auth.builder()
                .userId(1)
                .accessToken("123456789")
                .created(new Date())
                .expiresIn(new Date())
                .build();

        assertTrue(authDao.create(auth) > 0);
    }

    @Test
    public void updateTest() {
        Auth auth = Auth.builder()
                .id(1)
                .userId(1)
                .accessToken("$$$$$$$$")
                .created(new Date())
                .expiresIn(new Date())
                .build();

        assertEquals(1, authDao.update(auth));
    }

    @Test
    public void getAllTest() {
        List<Auth> auths = authDao.getAll();
        assertFalse(auths.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Auth auth = authDao.getById(1);
        assertEquals(1, auth.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, authDao.delete(2));
    }

    @Test
    public void getByAccessTokenTest() {
        List<Auth> auths = authDao.getByAccessToken("$123456#");
        assertFalse(auths.isEmpty());
    }
}
