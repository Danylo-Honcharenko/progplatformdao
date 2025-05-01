package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.UserDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.UserDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.User;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoImplTest {

    private final UserDaoI userDao = new UserDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createTest() {
        User user = User.builder()
                .first_name("User")
                .last_name("Test")
                .email("email@email.com")
                .password("password")
                .roleId(1)
                .created(new Date())
                .build();

        assertTrue(userDao.create(user) > 0);
    }

    @Test
    public void updateTest() {
        User user = User.builder()
                .id(1)
                .first_name("User 2")
                .last_name("Test 2")
                .email("email@email.com")
                .password("password")
                .roleId(1)
                .updated(new Date())
                .build();

        assertEquals(1, userDao.update(user));
    }

    @Test
    public void findByEmailTest() {
        List<User> user = userDao.findByEmail("testOper.test@icloud.com");
        assertFalse(user.isEmpty());
    }

    @Test
    public void getAllTest() {
        List<User> users = userDao.getAll();
        assertFalse(users.isEmpty());
    }

    @Test
    public void getByIdTest() {
        User user = userDao.getById(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, userDao.delete(4));
    }
}
