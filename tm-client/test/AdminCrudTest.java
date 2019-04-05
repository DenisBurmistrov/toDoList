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
import java.sql.SQLException;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminCrudTest {

    @Inject
    private SessionEndpoint sessionEndpoint;

    @Inject
    private AdminEndpoint adminEndpoint;

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private ServiceLocator serviceLocator;

    private Session session;

    private UserDto userDto;

    @Before
    public void initSessionAndUser() throws Exception_Exception {
        userEndpoint.logIn("admin", "admin");
        session = sessionEndpoint.getNewSession("b7801a28-00ec-4b21-92f5-940c9376488a");
        serviceLocator.setSession(session);
    }


    @Test
    public void t1_adminServiceCreateUserTest() throws Exception_Exception {
        adminEndpoint.createUser(session, "test", "test", "test", "test", "test", "@test", Role.COMMON_USER);
    }

    @Test
    public void t2_adminServiceUpdatePasswordTest() throws Exception_Exception {
        userDto = userEndpoint.logIn("test","test");
        adminEndpoint.updatePasswordById(session, userDto.getId(), "test1", "test1");
    }

    @Test
    public void t3_adminServiceMergeTest() throws Exception_Exception {
        adminEndpoint.updateUserByLogin(session, "test", "test1", "test1", "test1", "@test1", Role.COMMON_USER);
    }

    @Test
    public void t4_DadminServiceRemoveTest() throws Exception_Exception {
        userDto = userEndpoint.logIn("test","test");
        adminEndpoint.removeUserById(session ,userDto.getId());
    }
}
