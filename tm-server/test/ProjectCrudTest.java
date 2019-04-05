import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
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
    public void AprojectServicePersistTest() throws SQLException, NoSuchAlgorithmException, ParseException, IOException {
        projectService.persist("6c931f71-719c-44c7-a777-725957da3e7b", "test", "test", "11.11.2021", "Готово");
    }

    @Test
    public void BprojectServiceMergeTest() throws ParseException, SQLException {
        projectService.merge
                ("6c931f71-719c-44c7-a777-725957da3e7b",
                Objects.requireNonNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test")).getId(),
                "test1", "test1", "12.12.2021", "В процессе");
    }

    @Test
    public void CprojectServiceFindAllTest() throws SQLException {
        projectService.findAll("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void DprojectServiceFindOneByDescriptionTest() throws SQLException {
        projectService.findOneByDescription("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }

    @Test
    public void EprojectServiceFindOneByNameTest() throws SQLException {
        projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }


    @Test
    public void FprojectServiceFindAllSortByDateBeginTest() throws SQLException {
        projectService.findAllSortByDateBegin("6c931f71-719c-44c7-a777-725957da3e7b");
    }


    @Test
    public void GprojectServiceFindAllSortByDateEndTest() throws SQLException {
        projectService.findAllSortByDateEnd("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void HprojectServiceFindAllSortByStatusTest() throws SQLException {
        projectService.findAllSortByStatus("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void IprojectServiceRemoveTest() throws SQLException {
        projectService.remove
                ("6c931f71-719c-44c7-a777-725957da3e7b",
                Objects.requireNonNull(projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1")).getId());
    }

}
