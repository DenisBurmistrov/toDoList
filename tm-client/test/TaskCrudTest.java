import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.endpoint.*;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskCrudTest {

    @Inject
    private TaskEndpoint taskEndpoint;

    @Inject
    private SessionEndpoint sessionEndpoint;

    @Inject
    private ServiceLocator serviceLocator;

    private Session session;

    @Before
    public void initSessionAndUser() throws Exception_Exception {
        session = sessionEndpoint.getNewSession("b7801a28-00ec-4b21-92f5-940c9376488a");
        serviceLocator.setSession(session);
    }

    @Test
    public void t01_taskEndpointPersistTest() throws Exception_Exception {
        taskEndpoint.createTask(session, session.getUserId(), "b71619e2-d721-44d4-ba6b-a962ade5d914",
                "test", "test", "20.20.2020", "Готово");
        Assert.assertNotNull(taskEndpoint.findTaskByName(session, session.getUserId(), "test"));
    }

    @Test
    public void t02_taskEndpointMergeTest() throws Exception_Exception {
        taskEndpoint.updateTaskById
                (session, session.getUserId(), "b71619e2-d721-44d4-ba6b-a962ade5d914",
                        taskEndpoint.findTaskByName(session, session.getUserId(), "test").getId(),
                        "test1", "test1", "20.20.2020", "Готово");
        Assert.assertNotNull(taskEndpoint.findTaskByName(session, session.getUserId(), "test1"));
    }

    @Test
    public void t03_taskEndpointFindAllTasksTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findAllTasks(session, session.getUserId()));
    }

    @Test
    public void t04_taskEndpointFindAllTasksInProjectTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findAllTasksInProject(session, session.getUserId(), "b71619e2-d721-44d4-ba6b-a962ade5d914"));
    }

    @Test
    public void t05_taskEndpointFindAllTasksSortByDateBeginTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findAllTasksSortByDateBegin(session, session.getUserId()));
    }
    @Test
    public void t06_taskEndpointFindAllTasksSortByDateEndTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findAllTasksSortByDateEnd(session, session.getUserId()));
    }

    @Test
    public void t07_taskEndpointFindAllTasksSortByStatusTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findAllTasksSortByStatus(session, session.getUserId()));
    }

    @Test
    public void t08_taskEndpointFindTaskByDescriptionTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findTaskByDescription(session, session.getUserId(), "test1"));
    }

    @Test
    public void t09_taskEndpointFindTaskByNameTest() throws Exception_Exception {
        Assert.assertNotNull(taskEndpoint.findTaskByName(session, session.getUserId(), "test1"));
    }

    @Test
    public void t10_taskEndpointRemoveTaskByIdTest() throws Exception_Exception {
        taskEndpoint.removeTaskById(session, session.getUserId(), taskEndpoint.findTaskByName(session, session.getUserId(), "test1").getId());
        Assert.assertNull(taskEndpoint.findTaskByName(session, session.getUserId(), "test1"));
    }

}
