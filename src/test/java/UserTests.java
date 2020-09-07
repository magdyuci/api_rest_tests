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

        User user = userList.stream().filter(i -> i.getId() == 10).findFirst().get();
        Assert.assertTrue(user.getTitle().contains("optio molestias"));
        System.out.println(user.getTitle());
    }

    @Test
    public void createUser() throws Exception {
        UserApi userApi = new UserApi();

        User user = User.generateUserRandom();
        User createdUser = userApi.createUser(user);

        Assert.assertEquals(createdUser.getId(), new Long(101L));
        System.out.println(createdUser);
    }

    @Test
    public void updateUser() throws Exception {
        UserApi userApi = new UserApi();

        User user = userApi.getUser(2L);
        user.setTitle("My new title");
        user.setBody("My new body");
        user = userApi.updateUser(2L, user);
        Assert.assertEquals(user.getId(), new Long (2L));
    }

    @Test
    public void deleteUser() throws Exception {
        UserApi userApi = new UserApi();

        String deleteMS = userApi.deleteUser(3L);
        System.out.println(deleteMS);
        Assert.assertTrue(deleteMS.contains("200"));
    }
}
