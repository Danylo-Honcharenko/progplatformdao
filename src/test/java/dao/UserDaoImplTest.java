package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.UserDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.UserDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.User;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoImplTest {

    private UserDaoI userDao;

    @BeforeEach
    public void setUp() {
        this.userDao = new UserDaoImpl(Database.getDataSource());
    }

    @Test
    public void createTest() {
        User user = User.builder()
                .first_name("User")
                .last_name("Test")
                .email("email@email.com")
                .password("password")
                .roleId(1L)
                .created(new Date())
                .build();

        assertTrue(userDao.create(user) > 0);
    }

    @Test
    public void updateTest() {
        User user = User.builder()
                .id(1L)
                .first_name("User 2")
                .last_name("Test 2")
                .email("email@email.com")
                .password("password")
                .roleId(1L)
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
        List<User> user = userDao.getById(1L);
        assertFalse(CollectionUtils.isEmpty(user));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, userDao.delete(4L));
    }
}
