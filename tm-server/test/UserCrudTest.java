import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
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
    public void t1_userServiceLogInTest() throws SQLException, NoSuchAlgorithmException {
        Assert.assertNotNull(userService.logIn("admin", "admin"));
    }
}
