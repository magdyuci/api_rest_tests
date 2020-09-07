package pojos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class User {
    protected Long userId;
    protected Long id;
    protected String title;
    protected String body;

    public static User fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, User.class);
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static User generateUserRandom(){
        User user = new User();
        user.setUserId(2L);
        user.setId(101L);
        user.setTitle(RandomStringUtils.randomAlphabetic(10));
        user.setBody(RandomStringUtils.randomAlphabetic(25));

        return user;
    }
}
