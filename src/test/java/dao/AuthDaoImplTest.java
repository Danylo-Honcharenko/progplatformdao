package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.AuthDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.AuthDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AuthDaoImplTest {

    private AuthDaoI authDao;

    @BeforeEach
    public void setUp() {
        this.authDao = new AuthDaoImpl(Database.getDataSource());
    }

    @Test
    public void createAuth() {
        Auth auth = Auth.builder()
                .userId(1L)
                .created(new Date())
                .sid(UUID.randomUUID())
                .expiresAt(new Date())
                .revokedAt(new Date())
                .build();

        assertTrue(authDao.create(auth) > 0);
    }

    @Test
    public void updateTest() {
        Auth auth = Auth.builder()
                .id(1L)
                .userId(1L)
                .created(new Date())
                .sid(UUID.randomUUID())
                .expiresAt(new Date())
                .revokedAt(new Date())
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
        List<Auth> auth = authDao.getById(1L);
        assertFalse(CollectionUtils.isEmpty(auth));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, authDao.delete(2L));
    }

    @Test
    public void getByAccessTokenTest() {
        List<Auth> auths = authDao.getByUserId(1L);
        assertFalse(auths.isEmpty());
    }
}
