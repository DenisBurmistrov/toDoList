import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
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
    public void AtaskServicePersistTest() throws SQLException, ParseException, NoSuchAlgorithmException, IOException {
        taskService.persist
                ("6c931f71-719c-44c7-a777-725957da3e7b", "b71619e2-d721-44d4-ba6b-a962ade5d914", "test", "test",
                        "11.11.2021", "Готово");
    }

    @Test
    public void BtaskServiceMergeTest() throws SQLException, ParseException {
        taskService.merge("6c931f71-719c-44c7-a777-725957da3e7b","b71619e2-d721-44d4-ba6b-a962ade5d914",
                Objects.requireNonNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test")).getId(),
                "test1", "test1", "11.11.2022", "В процессе");
    }

    @Test
    public void CtaskServiceFindAllTest() throws SQLException {
        taskService.findAll("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void DtaskServiceFindAllInProjectTest() throws SQLException {
        taskService.findAllInProject("6c931f71-719c-44c7-a777-725957da3e7b", "b71619e2-d721-44d4-ba6b-a962ade5d914");
    }

    @Test
    public void EtaskServiceFindAllSortByDateBeginTest() throws SQLException {
        taskService.findAllSortByDateBegin("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void FtaskServiceFindAllSortByDateEndTest() throws SQLException {
        taskService.findAllSortByDateEnd("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void GtaskServiceFindAllSortByStatusTest() throws SQLException {
        taskService.findAllSortByStatus("6c931f71-719c-44c7-a777-725957da3e7b");
    }

    @Test
    public void HtaskServiceFindOneByNameTest() throws SQLException {
        taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }

    @Test
    public void ItaskServiceFindOneByDescriptionTest() throws SQLException {
        taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1");
    }

    @Test
    public void JtaskServiceRemoveTest() throws SQLException {
        taskService.remove("6c931f71-719c-44c7-a777-725957da3e7b",
                Objects.requireNonNull(taskService.findOneByName("6c931f71-719c-44c7-a777-725957da3e7b", "test1")).getId());
    }


}
