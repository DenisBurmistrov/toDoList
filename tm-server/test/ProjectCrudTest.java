import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.enumerated.Status;
import ru.burmistrov.tm.repository.IProjectRepository;
import ru.burmistrov.tm.service.ProjectService;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

@RunWith(CdiTestRunner.class)
public class ProjectCrudTest {

    @Inject
    private IProjectService projectService;

    private Project project;

    @Test
    public void projectServicePersistTest() throws SQLException, NoSuchAlgorithmException, ParseException, IOException {
        project = projectService.persist("6c931f71-719c-44c7-a777-725957da3e7b", "test", "test", "11.11.2021", "Готово");
    }

    @Test
    public void projectServiceMergeTest() throws ParseException, SQLException {
        projectService.merge("6c931f71-719c-44c7-a777-725957da3e7b", project.getId(), "test1", "test1", "12.12.2021", "В процессе");
    }

    @Test
    public void projectServiceFindAllTest() throws SQLException {
        projectService.findAll("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void projectServiceFindOneByDescriptionTest() throws SQLException {
        projectService.findOneByDescription("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }

    @Test
    public void projectServiceFindOneByNameTest() throws SQLException {
        projectService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }


    @Test
    public void projectServiceFindAllSortByDateBeginTest() throws SQLException {
        projectService.findAllSortByDateBegin("6c931f71-719c-44c7-a777-725957da3e7b");
    }


    @Test
    public void projectServiceFindAllSortByDateEndTest() throws SQLException {
        projectService.findAllSortByDateEnd("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void projectServiceFindAllSortByStatusTest() throws SQLException {
        projectService.findAllSortByStatus("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void projectServiceRemoveTest() throws SQLException {
        projectService.remove("6c931f71-719c-44c7-a777-725957da3e7b", project.getId());
    }

}
