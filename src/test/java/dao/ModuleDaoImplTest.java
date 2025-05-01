package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.ModuleDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.ModuleDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Module;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModuleDaoImplTest {

    private final ModuleDaoI moduleDao = new ModuleDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createTest() {
        Module module = Module.builder()
                .name("test")
                .description("test")
                .courseId(1)
                .created(new Date())
                .build();

        assertTrue(moduleDao.create(module) > 0);
    }

    @Test
    public void updateTest() {
        Module module = Module.builder()
                .id(1)
                .name("test")
                .description("test")
                .courseId(1)
                .created(new Date())
                .build();

        assertEquals(1, moduleDao.update(module));
    }

    @Test
    public void getAllTest() {
        List<Module> modules = moduleDao.getAll();
        assertFalse(modules.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Module module = moduleDao.getById(1);
        assertEquals(1, module.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, moduleDao.delete(2));
    }

    @Test
    public void getModulesByCourseIdTest() {
        List<Module> modules = moduleDao.getModulesByCourseId(1);
        assertFalse(modules.isEmpty());
    }
}
