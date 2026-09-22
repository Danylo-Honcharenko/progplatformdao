package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.ModuleStatDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.ModuleStatImpl;
import org.ua.fkrkm.proglatformdao.entity.ModuleStat;
import org.ua.fkrkm.proglatformdao.entity.view.ModuleStateView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModuleStatImplTest {

    private ModuleStatDaoI moduleCompleteDao;

    @BeforeEach
    public void setUp() {
        this.moduleCompleteDao = new ModuleStatImpl(Database.getDataSource());
    }

    @Test
    public void createTest() {
        ModuleStat moduleStat = ModuleStat.builder()
                .moduleId(1L)
                .topicId(2L)
                .userId(1L)
                .build();

        assertTrue(moduleCompleteDao.create(moduleStat) > 0);
    }

    @Test
    public void updateTest() {
        ModuleStat moduleStat = ModuleStat.builder()
                .id(1L)
                .moduleId(1L)
                .topicId(2L)
                .userId(2L)
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
        List<ModuleStat> moduleStat = moduleCompleteDao.getById(1L);
        assertFalse(CollectionUtils.isEmpty(moduleStat));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, moduleCompleteDao.delete(2L));
    }

    @Test
    public void findModuleCompleteByUserIdTest() {
        List<ModuleStat> moduleStats = moduleCompleteDao.findModuleStatByUserId(2L);
        assertFalse(moduleStats.isEmpty());
    }

    @Test
    public void findModulesStatByUserIdTest() {
        List<ModuleStateView> modulesStatByUserId = moduleCompleteDao.findModulesStatByUserId(1L);
        assertFalse(modulesStatByUserId.isEmpty());
    }
}
