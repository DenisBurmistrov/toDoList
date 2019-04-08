import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.burmistrov.tm.configuration.SpringConfiguration;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.UserEndpoint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCrudTest {

    @Autowired
    private UserEndpoint userEndpoint;

    @Test
    public void AUserEndpointLogInTest() throws Exception_Exception {
    Assert.assertNotNull(userEndpoint.logIn("admin", "admin"));
    }
}
