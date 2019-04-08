import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.configuration.SpringConfiguration;
import ru.burmistrov.tm.endpoint.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectCrudTest {

    @Autowired
    private
    ProjectEndpoint projectEndpoint;

    @Autowired
    private
    SessionEndpoint sessionEndpoint;

    @Autowired
    private
    ServiceLocator serviceLocator;

    private Session session;

    @Before
    public void initSessionAndUser() throws Exception_Exception {
        session = sessionEndpoint.getNewSession("b7801a28-00ec-4b21-92f5-940c9376488a");
        serviceLocator.setSession(session);
    }

    @Test
    public void t1_projectEndpointPersist() throws Exception_Exception {
        projectEndpoint.createProject(session, session.getUserId(), "test", "test", "20.20.2020", "Готово");
        Assert.assertNotNull(projectEndpoint.findProjectByName(session, session.getUserId(), "test"));
    }

    @Test
    public void t2_projectEndpointUpdate() throws Exception_Exception {
        projectEndpoint.updateProjectById(session, session.getUserId(),
                projectEndpoint.findProjectByName(session, session.getUserId(), "test").getId(), "test1", "test1", "20.20.2020","Готово");
        Assert.assertNotNull(projectEndpoint.findProjectByName(session, session.getUserId(), "test1"));
    }

    @Test
    public void t3_projectEndPointFindAllProjects() throws Exception_Exception {
        Assert.assertNotNull(projectEndpoint.findAllProjects(session, session.getUserId()));
    }

    @Test
    public void t4_projectEndpointFindAllProjectsSortByDateBegin() throws Exception_Exception {
        Assert.assertNotNull(projectEndpoint.findAllProjectsSortByDateBegin(session, session.getUserId()));
    }

    @Test
    public void t5_projectEndpointFindAllProjectsSortByDateEnd() throws Exception_Exception {
        Assert.assertNotNull(projectEndpoint.findAllProjectsSortByDateEnd(session, session.getUserId()));
    }

    @Test
    public void t6_projectEndpointFindAllProjectsSortByStatus() throws Exception_Exception {
        Assert.assertNotNull(projectEndpoint.findAllProjectsSortByStatus(session, session.getUserId()));
    }

    @Test
    public void t7_projectEndpointFindProjectByDescription() throws Exception_Exception {
        Assert.assertNotNull(projectEndpoint.findProjectByDescription(session, session.getUserId(), "test1"));
    }

    @Test
    public void t8_projectEndpointFindProjectByName() throws Exception_Exception {
        Assert.assertNotNull(projectEndpoint.findProjectByName(session, session.getUserId(), "test1"));
    }

    @Test
    public void t9_projectEndpointRemove() throws Exception_Exception {
        projectEndpoint.removeProjectById
                (session, session.getUserId(),
                projectEndpoint.findProjectByName(session, session.getUserId(), "test1").getId());
        Assert.assertNull(projectEndpoint.findProjectByName(session, session.getUserId(), "test1"));
    }

}
