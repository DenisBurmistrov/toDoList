import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.configuration.SpringConfiguration;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class UserCrudTest {

    @Autowired
    private IUserService userService;

    @Test
    public void t1_userServiceLogInTest() throws SQLException, NoSuchAlgorithmException {
        Assert.assertNotNull(userService.logIn("admin", "admin"));
    }
}
