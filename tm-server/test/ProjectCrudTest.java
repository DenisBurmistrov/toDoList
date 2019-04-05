import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.burmistrov.tm.api.service.IProjectService;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectCrudTest {

    @Inject
    private IProjectService projectService;

    @Test
    public void t1_projectServicePersistTest() throws SQLException, NoSuchAlgorithmException, ParseException, IOException {
        projectService.persist("6c931f71-719c-44c7-a777-725957da3e7b", "test", "test", "11.11.2021", "Готово");
        Assert.assertNotNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b" ,"test"));
    }

    @Test
    public void t2_projectServiceMergeTest() throws ParseException, SQLException {
        projectService.merge
                ("6c931f71-719c-44c7-a777-725957da3e7b",
                Objects.requireNonNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test")).getId(),
                "test1", "test1", "12.12.2021", "В процессе");
        Assert.assertNotNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b" ,"test1"));
    }

    @Test
    public void t3_projectServiceFindAllTest() throws SQLException {
        Assert.assertNotNull(projectService.findAll("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t4_projectServiceFindOneByDescriptionTest() throws SQLException {
        Assert.assertNotNull(projectService.findOneByDescription("6c931f71-719c-44c7-a777-725957da3e7b", "test1"));
    }

    @Test
    public void t5_projectServiceFindOneByNameTest() throws SQLException {
        Assert.assertNotNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1"));
    }


    @Test
    public void t6_projectServiceFindAllSortByDateBeginTest() throws SQLException {
        Assert.assertNotNull(projectService.findAllSortByDateBegin("6c931f71-719c-44c7-a777-725957da3e7b"));
    }


    @Test
    public void t7_projectServiceFindAllSortByDateEndTest() throws SQLException {
        Assert.assertNotNull(projectService.findAllSortByDateEnd("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t8_projectServiceFindAllSortByStatusTest() throws SQLException {
        Assert.assertNotNull(projectService.findAllSortByStatus("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t9_projectServiceRemoveTest() throws SQLException {
        projectService.remove
                ("6c931f71-719c-44c7-a777-725957da3e7b",
                Objects.requireNonNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1")).getId());
        Assert.assertNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b" ,"test1"));
    }

}
