import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.configuration.SpringConfiguration;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminCrudTest {

    @Autowired
    private IAdminService adminService;

    @Test
    public void t1_adminServicePersistTest() throws SQLException, NoSuchAlgorithmException, IOException {
        adminService.createUser("test", "test", "test", "test", "test", "@test", Role.COMMON_USER);
        Assert.assertNotNull(adminService.findOneByLogin("test"));
    }

    @Test
    public void t2_adminServiceUpdatePasswordTest() throws SQLException, NoSuchAlgorithmException {
        @NotNull final User user = new User();
        user.setHashPassword("test");
        adminService.updatePassword("test", "test1");
        Assert.assertNotEquals("test", adminService.findOneByLogin("test").getPassword(), user.getPassword());
    }

    @Test
    public void t3_adminServiceMergeTest() throws SQLException {
        adminService.updateUserByLogin("test", "test1", "test1", "test1", "@test1", Role.COMMON_USER);
        Assert.assertNotEquals("test", adminService.findOneByLogin("test").getFirstName(), "test");
    }

    @Test
    public void t4_adminServiceRemoveTest() throws SQLException {
        adminService.removeUserById(adminService.findOneByLogin("test").getId());
        Assert.assertNull(adminService.findOneByLogin("test"));
    }

}
