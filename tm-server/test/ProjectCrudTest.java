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
    private
    IProjectRepository projectRepository;

    @Inject
    IProjectService projectService;

    private Project project;

    @Test
    @Transactional
    public void persistTest() {
        project = new Project();
        project.setUserId("6c931f71-719c-44c7-a777-725957da3e7b");
        project.setName("test");
        project.setDescription("test");
        project.setDateBegin(new Date());
        project.setDateEnd(new Date());
        project.setStatus(Status.COMPLETE);
        projectRepository.persist(project);
    }

    @Test
    public void ServicePersistTest() throws SQLException, NoSuchAlgorithmException, ParseException, IOException {
        projectService.persist("6c931f71-719c-44c7-a777-725957da3e7b", "test", "test", "11.11.2021", "Готово");
    }

}
