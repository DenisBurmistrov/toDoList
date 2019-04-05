import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.endpoint.*;

import javax.inject.Inject;
import java.util.Date;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectCrudTest {

    @Inject
    ProjectEndpoint projectEndpoint;

    @Inject
    SessionEndpoint sessionEndpoint;

    @Inject
    ServiceLocator serviceLocator;

    private Session session;

    private ProjectDto projectDto;


    @Before
    public void initSessionAndUser() throws Exception_Exception {
        session = sessionEndpoint.getNewSession("b7801a28-00ec-4b21-92f5-940c9376488a");
        serviceLocator.setSession(session);
    }

    @Test
    public void t1_projectEndpointPersist() throws Exception_Exception {
        projectEndpoint.createProject(session, session.getUserId(), "test", "test", "20.20.2020", "Готово");
    }

    @Test
    public void t2_projectEndpointUpdate() throws Exception_Exception {
        projectEndpoint.updateProjectById(session, session.getUserId(),
                projectEndpoint.findProjectByName(session, session.getUserId(), "test").getId(), "test", "test", "20.20.2020","Готово");
    }

    @Test
    public void t3_projectEndPointFindAllProjects() throws Exception_Exception {
        projectEndpoint.findAllProjects(session, session.getUserId());
    }

    @Test
    public void t4_projectEndpointFindAllProjectsSortByDateBegin() throws Exception_Exception {
        projectEndpoint.findAllProjectsSortByDateBegin(session, session.getUserId());
    }

    @Test
    public void t5_projectEndpointFindAllProjectsSortByDateEnd() throws Exception_Exception {
        projectEndpoint.findAllProjectsSortByDateEnd(session, session.getUserId());
    }

    @Test
    public void t6_projectEndpointFindAllProjectsSortByStatus() throws Exception_Exception {
        projectEndpoint.findAllProjectsSortByStatus(session, session.getUserId());
    }

    @Test
    public void t7_projectEndpointFindProjectByDescription() throws Exception_Exception {
        projectEndpoint.findProjectByDescription(session, session.getUserId(), "test");
    }

    @Test
    public void t8_projectEndpointFindProjectByName() throws Exception_Exception {
        projectEndpoint.findProjectByName(session, session.getUserId(), "test");
    }

    @Test
    public void t9_projectEndpointFindProjectByName() throws Exception_Exception {
        projectEndpoint.removeProjectById
                (session, session.getUserId(),
                projectEndpoint.findProjectByName(session, session.getUserId(), "test").getId());
    }

}
