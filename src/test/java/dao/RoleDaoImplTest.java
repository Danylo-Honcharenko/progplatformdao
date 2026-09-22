package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ua.fkrkm.proglatformdao.dao.RoleDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.RoleDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoleDaoImplTest {

    private RoleDaoI roleDao;

    @BeforeEach
    public void setUp() {
        this.roleDao = new RoleDaoImpl(Database.getDataSource());
    }

    @Test
    public void findByNameTest() {
        List<Role> roles = roleDao.findIdByName("ROLE_USER");
        assertFalse(roles.isEmpty());
    }
}
