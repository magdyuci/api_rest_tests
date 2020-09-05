import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.User;

import java.util.List;

public class UserTests {

    @Test
    public void usersList() throws Exception {
        UserApi userApi = new UserApi();
        List<User> userList = userApi.getUsers();

        Assert.assertNotNull(userList);
        Assert.assertEquals(userList.size(), 100);
        System.out.println(userList.size());
    }
}
