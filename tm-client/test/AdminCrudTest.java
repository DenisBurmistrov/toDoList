import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RunWith(CdiTestRunner.class)
public class AdminCrudTest {

    @Inject
    private AdminEndpoint adminEndpoint;

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private SessionEndpoint sessionEndpoint;

    @Inject
    private ServiceLocator serviceLocator;

    private Session session;


    @Test
    public void userEndpointLogIn() throws Exception_Exception {
        session = sessionEndpoint.getNewSession("ID");
        serviceLocator.setSession(session);
    }

    @Test
    public void adminServicePersistTest() {
        adminEndpoint.createUser("test", "test", "test", "test", "test", "@test", Role.COMMON_USER);
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
