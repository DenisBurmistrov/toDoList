import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.enumerated.Role;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RunWith(CdiTestRunner.class)
public class UserCrudTest {

    @Inject private IUserService userService;

    @Test
    public void userServiceLogInTest() throws SQLException, NoSuchAlgorithmException {
        userService.logIn("admin", "admin");
    }

    @Test
    public void taskServiceMergeTest() throws SQLException {
        userService.merge("6c931f71-719c-44c7-a777-725957da3e7b", "adminTest", "adminTest", "adminTest", "@adminTest", Role.ADMINISTRATOR);
    }

    @Test
    public void taskServiceReMergeTest() throws SQLException {
        userService.merge("6c931f71-719c-44c7-a777-725957da3e7b", "admin", "admin", "admin", "@admin", Role.ADMINISTRATOR);
    }
}
