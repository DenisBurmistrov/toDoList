import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.UserEndpoint;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCrudTest {

    @Inject
    private UserEndpoint userEndpoint;

    @Test
    public void AUserEndpointLogInTest() throws Exception_Exception {
    Assert.assertNotNull(userEndpoint.logIn("admin", "admin"));
    }
}
