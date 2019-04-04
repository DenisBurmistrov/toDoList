import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RunWith(CdiTestRunner.class)
public class AdminCrudTest {

    @Inject
    private IAdminService adminService;

    private User user;
    @Test
    public void adminServicePersistTest() throws SQLException, NoSuchAlgorithmException, IOException {
        user = adminService.createUser("test", "test", "test", "test", "test", "@test", Role.COMMON_USER);
    }

    @Test
    public void adminServiceUpdatePasswordTest() throws SQLException, NoSuchAlgorithmException {
        adminService.updatePassword("test", "test1");
    }

    @Test
    public void adminServiceMergeTest() throws SQLException {
        adminService.updateUserById(user.getId(), "test1", "test1", "test1", "@test1", Role.COMMON_USER);
    }

    @Test
    public void adminServiceRemoveTest() throws SQLException {
        adminService.removeUserById(user.getId());
    }

}
