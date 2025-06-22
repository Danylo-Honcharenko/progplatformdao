package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.ModuleStatDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.ModuleStatImpl;
import org.ua.fkrkm.proglatformdao.entity.ModuleStat;
import org.ua.fkrkm.proglatformdao.entity.view.ModuleStateView;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModuleStatImplTest {

    private final ModuleStatDaoI moduleCompleteDao = new ModuleStatImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createTest() {
        ModuleStat moduleStat = ModuleStat.builder()
                .moduleId(1)
                .topicId(1)
                .userId(1)
                .build();

        assertTrue(moduleCompleteDao.create(moduleStat) > 0);
    }

    @Test
    public void updateTest() {
        ModuleStat moduleStat = ModuleStat.builder()
                .id(1)
                .moduleId(1)
                .topicId(1)
                .userId(2)
                .build();

        assertEquals(1, moduleCompleteDao.update(moduleStat));
    }

    @Test
    public void getAllTest() {
        List<ModuleStat> modules = moduleCompleteDao.getAll();
        assertFalse(modules.isEmpty());
    }

    @Test
    public void getByIdTest() {
        ModuleStat moduleStat = moduleCompleteDao.getById(1);
        assertEquals(1, moduleStat.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, moduleCompleteDao.delete(2));
    }

    @Test
    public void findModuleCompleteByUserIdTest() {
        List<ModuleStat> moduleStats = moduleCompleteDao.findModuleStatByUserId(1);
        assertFalse(moduleStats.isEmpty());
    }

    @Test
    public void findModulesStatByUserIdTest() {
        List<ModuleStateView> modulesStatByUserId = moduleCompleteDao.findModulesStatByUserId(1);
        assertFalse(modulesStatByUserId.isEmpty());
    }
}
