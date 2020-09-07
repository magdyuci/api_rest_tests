import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiManager {

    private static final String USER_AGENT = "application/json";

    public String sendGET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        String response = requestResult(con);
        con.disconnect();
        return response;
    }

    public String sendPOST(String url, String postData) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(postData);
        writer.flush();
        writer.close();

        System.out.println(postData);
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        String response = requestResult(con);
        con.disconnect();
        return response;
    }

    public String sendPUT(String url, String putData) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(putData);
        writer.flush();
        writer.close();
        System.out.println(putData);

        int responseCode = con.getResponseCode();
        System.out.println("PUT Response Code :: " + responseCode);

        String response = requestResult(con);
        con.disconnect();
        return response;
    }

    public String sendDELETE(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("DELETE Response Code :: " + responseCode);

        String response = responseCode + ": " + requestResult(con);
        con.disconnect();
        return response;
    }

    public String requestResult(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        String response = "";
        String inputLine;

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { // success code
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
