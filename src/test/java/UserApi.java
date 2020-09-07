import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
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

    public User getUser(Long userId) throws Exception {
        ApiManager apiManager = new ApiManager();

        String response = apiManager.sendGET("https://jsonplaceholder.typicode.com/posts/" + userId);
        JSONObject jsonUser = new JSONObject(response);

        return User.fromJson(jsonUser.toString());
    }

    public User createUser(User user) throws Exception {
        ApiManager apiManager = new ApiManager();
        String response = apiManager.sendPOST("https://jsonplaceholder.typicode.com/posts/", user.toJSON());

        JSONObject jsonUser = new JSONObject(response);

        return User.fromJson(jsonUser.toString());
    }

    public User updateUser(Long userId, User user) throws Exception {
        ApiManager apiManager = new ApiManager();
        String response = apiManager.sendPUT("https://jsonplaceholder.typicode.com/posts/" + userId, user.toJSON());

        JSONObject jsonUser = new JSONObject(response);

        return User.fromJson(jsonUser.toString());
    }

    public String deleteUser(Long userID) throws Exception {
        ApiManager apiManager = new ApiManager();
        String response = apiManager.sendDELETE("https://jsonplaceholder.typicode.com/posts/" + userID);

        return response;
    }
}
