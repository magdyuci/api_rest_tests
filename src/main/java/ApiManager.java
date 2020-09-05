import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiManager {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://jsonplaceholder.typicode.com/posts/";

    public static void sendGET() throws Exception {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    public String sendGET(String url) throws Exception {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        String response = requestResult(con);
        con.disconnect();
        return response;
    }

    public String requestResult(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        String response = "";
        String inputLine;

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            // print result
            System.out.println(response);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            throw new Exception(responseCode + " - " + response);
        }
        return response;
    }

}
