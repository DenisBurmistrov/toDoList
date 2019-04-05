import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
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

    @Before
    public void initSessionAndUser() throws Exception_Exception {
        userEndpoint.logIn("admin", "admin");
        session = sessionEndpoint.getNewSession("b7801a28-00ec-4b21-92f5-940c9376488a");
        serviceLocator.setSession(session);
    }


    @Test
    public void t1_adminServiceCreateUserTest() throws Exception_Exception {
        Assert.assertNotNull(adminEndpoint.createUser(session, "test", "test", "test", "test", "test", "@test", Role.COMMON_USER));
    }

    @Test
    public void t2_adminServiceUpdatePasswordTest() throws Exception_Exception, ValidateAccessException_Exception, CloneNotSupportedException_Exception, NoSuchAlgorithmException_Exception {
        @NotNull final UserDto userDto = userEndpoint.logIn("test","test");
        adminEndpoint.updatePasswordById(session, userDto.getId(), "test", "test1");
        Assert.assertNotEquals("test", adminEndpoint.findOneByLogin(session, "test").getPassword(), userDto.getPassword());
    }

    @Test
    public void t3_adminServiceMergeTest() throws Exception_Exception, ValidateAccessException_Exception, CloneNotSupportedException_Exception, NoSuchAlgorithmException_Exception {
        adminEndpoint.updateUserByLogin(session, "test", "test1", "test1", "test1", "@test1", Role.COMMON_USER);
        Assert.assertEquals("test", adminEndpoint.findOneByLogin(session, "test").getFirstName(), "test1");
    }

    @Test
    public void t4_adminServiceRemoveTest() throws Exception_Exception, ValidateAccessException_Exception, CloneNotSupportedException_Exception, NoSuchAlgorithmException_Exception {
        @NotNull final UserDto userDto = userEndpoint.logIn("test","test1");
        adminEndpoint.removeUserById(session ,userDto.getId());
        Assert.assertNull(adminEndpoint.findOneByLogin(session, "test"));
    }
}
