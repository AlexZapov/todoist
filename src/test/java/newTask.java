import com.fasterxml.uuid.Generators;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.UUID;


public class newTask {

    private CloseableHttpClient httpClient; //= HttpClients.createDefault();
    private final String TOKEN = "bb1ad035d03c09d62297a4067e979ef72f2ebe06";
    private con long uuid_same = System.currentTimeMillis();
    UUID uuid = Generators.randomBasedGenerator().generate();
    StringBuilder json = new StringBuilder();

    @BeforeMethod
    public void setUp() throws Exception{
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void test_200_OK() throws Exception {
        System.out.println("Task sucessfully created without requirement parameters");
        System.out.println(uuid_same);
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same), json.append("{").append("\"content\":\"Appointment\"").append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_Ingnored() throws Exception {
        System.out.println("Same uuid");
        System.out.println(uuid_same);
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same), json.append("{").append("\"content\":\"Appointment\"").append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_Emptycontent() throws Exception {
        System.out.println("There is no parameters in request body");
        try {
            String result = sendPost(TOKEN, uuid.toString(), json.append("{").append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendPost(String token, String uuid, StringBuilder requestBody) throws Exception {

        String result = "";
        HttpPost post = new HttpPost("https://api.todoist.com/rest/v1/tasks");
        post.setHeader("Authorization", "Bearer " + token);
        post.setHeader("X-Request-Id", uuid);
        post.setHeader("Content-Type", "application/json");

//        StringBuilder json = new StringBuilder();
//        json.append("{");
//        //json.append("\content\": \"App\"");
//        json.append("\"content\":\"Appointment\"");
//        json.append("}");

        post.setEntity(new StringEntity(requestBody.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(response.getStatusLine().getStatusCode());
            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

    @AfterMethod
    public void close() throws IOException {
        httpClient.close();
    }
}
