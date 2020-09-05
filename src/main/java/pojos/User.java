package pojos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

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
}
