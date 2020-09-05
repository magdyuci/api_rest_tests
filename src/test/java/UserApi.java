import org.json.JSONArray;
import pojos.User;

import java.util.ArrayList;
import java.util.List;

public class UserApi {

    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        ApiManager apiManager = new ApiManager();

        String response = apiManager.sendGET("https://jsonplaceholder.typicode.com/posts/");
        JSONArray jsonUsers = new JSONArray(response);
        for (int i = 0; i < jsonUsers.length(); i++) {
            users.add(User.fromJson(jsonUsers.getJSONObject(i).toString()));
        }

        return users;
    }
}
