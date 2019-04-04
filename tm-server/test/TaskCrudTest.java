import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.ITaskRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

@RunWith(CdiTestRunner.class)
public class TaskCrudTest {

    @Inject
    private ITaskService taskService;

    private Task task;

    @Test
    public void taskServicePersistTest() throws SQLException, ParseException, NoSuchAlgorithmException, IOException {
        task = taskService.persist("6c931f71-719c-44c7-a777-725957da3e7b", "ID", "test", "test", "11.11.2021", "Готово");
    }

    @Test
    public void taskServiceMergeTest() throws SQLException, ParseException {
        taskService.merge("6c931f71-719c-44c7-a777-725957da3e7b","ID", task.getId(), "test1", "test1", "11.11.2022", "В процессе");
    }

    @Test
    public void taskServiceFindAllTest() throws SQLException {
        taskService.findAll("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void taskServiceFindAllInProjectTest() throws SQLException {
        taskService.findAllInProject("6c931f71-719c-44c7-a777-725957da3e7b", "ID");
    }

    @Test
    public void taskServiceFindAllSortByDateBeginTest() throws SQLException {
        taskService.findAllSortByDateBegin("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void taskServiceFindAllSortByDateEndTest() throws SQLException {
        taskService.findAllSortByDateEnd("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void taskServiceFindAllSortByStatusTest() throws SQLException {
        taskService.findAllSortByStatus("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void taskServiceFindOneByNameTest() throws SQLException {
        taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }

    @Test
    public void taskServiceFindOneByDescriptionTest() throws SQLException {
        taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }

    @Test
    public void taskServiceRemoveTest() throws SQLException {
        taskService.remove("6c931f71-719c-44c7-a777-725957da3e7b", task.getId());
    }


}
