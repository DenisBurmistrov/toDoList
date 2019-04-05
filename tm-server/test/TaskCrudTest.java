import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.ITaskRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskCrudTest {

    @Inject
    private ITaskService taskService;

    @Test
    public void t01_taskServicePersistTest() throws SQLException, ParseException, NoSuchAlgorithmException, IOException {
        taskService.persist
                ("6c931f71-719c-44c7-a777-725957da3e7b", "b71619e2-d721-44d4-ba6b-a962ade5d914", "test", "test",
                        "11.11.2021", "Готово");
        Assert.assertNotNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test"));
    }

    @Test
    public void t02_taskServiceMergeTest() throws SQLException, ParseException {
        @Nullable final Task task = taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test");
        Assert.assertNotNull(task);
        taskService.merge("6c931f71-719c-44c7-a777-725957da3e7b", "b71619e2-d721-44d4-ba6b-a962ade5d914",
                task.getId(), "test1", "test1", "11.11.2022", "В процессе");
        Assert.assertNotNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1"));
    }

    @Test
    public void t03_taskServiceFindAllTest() throws SQLException {
        Assert.assertNotNull(taskService.findAll("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t04_taskServiceFindAllInProjectTest() throws SQLException {
        Assert.assertNotNull(taskService.findAllInProject("6c931f71-719c-44c7-a777-725957da3e7b", "b71619e2-d721-44d4-ba6b-a962ade5d914"));
    }

    @Test
    public void t05_taskServiceFindAllSortByDateBeginTest() throws SQLException {
        Assert.assertNotNull(taskService.findAllSortByDateBegin("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t06_taskServiceFindAllSortByDateEndTest() throws SQLException {
        Assert.assertNotNull(taskService.findAllSortByDateEnd("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t07_taskServiceFindAllSortByStatusTest() throws SQLException {
        Assert.assertNotNull(taskService.findAllSortByStatus("6c931f71-719c-44c7-a777-725957da3e7b"));
    }

    @Test
    public void t08_taskServiceFindOneByNameTest() throws SQLException {
        Assert.assertNotNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1"));
    }

    @Test
    public void t09_taskServiceFindOneByDescriptionTest() throws SQLException {
        Assert.assertNotNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1"));
    }

    @Test
    public void t10_taskServiceRemoveTest() throws SQLException {
        @Nullable final Task task = taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
        Assert.assertNotNull(task);
        taskService.remove("6c931f71-719c-44c7-a777-725957da3e7b", task.getId());
        Assert.assertNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1"));
    }


}
