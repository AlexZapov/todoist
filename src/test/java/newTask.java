import com.fasterxml.uuid.Generators;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.*;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.*;
import java.util.*;


public class newTask {

    private CloseableHttpClient httpClient;
    private final String TOKEN = "bb1ad035d03c09d62297a4067e979ef72f2ebe06";
    private long uuid_same2 = System.currentTimeMillis();
    private long uuid_same = uuid_same2;
    private final String CONTENT = "Test New Task_" + uuid_same2;
    private final long PROJECT_ID = 2233085179L;
    private final int SECTION_ID = 0;
    private final int ORDER = 1;
    private final int PRIORITY = 4;
    private final int[] LABEL_IDS = {5, 10};
    private final String DUE_STRING = "Tomorrow";
    private final String DUE_STRING_RU = "Завтра";
    private final String DUE_LANG = "RU";
    private final String DUE_DATE = "2020-04-08";
    private final String DUE_DATETIME = "2020-04-09T19:42:27+00:00";
    UUID uuid = Generators.randomBasedGenerator().generate();
    StringBuilder json = new StringBuilder();

    @BeforeMethod
    public void setUp(){
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void test_200_OK_RequiredParams() throws Exception {
        System.out.println("Task sucessfully created without unrequirement parameters");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                    .append("\"content\":\"" + CONTENT + "\"")
                    .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_200_OK_AllParams() throws Exception {
        System.out.println("Task sucessfully creating");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"project_id\":" + PROJECT_ID + ",")
                            .append("\"section_id\":" + SECTION_ID + ",")
                            .append("\"order\":" + ORDER + ",")
                            .append("\"priority\":" + PRIORITY + ",")
                            .append("\"due_date\":\"" + DUE_DATE + "\",")
                            .append("\"label_ids:")
                            .append("[")
                            .append("{")
                            .append("\"id\":" + LABEL_IDS[0])
                            .append("}")
                            .append(",")
                            .append("{")
                            .append("\"id\":" + LABEL_IDS[1])
                            .append("}")
                            .append("]")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_200_OK_AllParams_due_date() throws Exception {
        System.out.println("Task sucessfully created without unrequirement parameters and due_date");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"project_id\":" + PROJECT_ID + ",")
                            .append("\"section_id\":" + SECTION_ID + ",")
                            .append("\"order\":" + ORDER + ",")
                            .append("\"priority\":" + PRIORITY + ",")
                            .append("\"due_date\":\"" + DUE_DATE + "\"")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_200_OK_AllParams_due_string() throws Exception {
        System.out.println("Task sucessfully created without unrequirement parameters and due_string");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"project_id\":" + PROJECT_ID + ",")
                            .append("\"section_id\":" + SECTION_ID + ",")
                            .append("\"order\":" + ORDER + ",")
                            .append("\"priority\":" + PRIORITY + ",")
                            .append("\"due_string\":\"" + DUE_STRING + "\"")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_Date_is_invalid() throws Exception {
        System.out.println("Task sucessfully created without unrequirement parameters and due_string");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"project_id\":" + PROJECT_ID + ",")
                            .append("\"section_id\":" + SECTION_ID + ",")
                            .append("\"order\":" + ORDER + ",")
                            .append("\"priority\":" + PRIORITY + ",")
                            .append("\"due_string\":\"" + DUE_STRING_RU + "\",")
                            .append("\"due_lang\":\"" + DUE_LANG + "\"")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_200_OK_AllParams_due_datetime() throws Exception {
        System.out.println("Task sucessfully created without unrequirement parameters and due_string");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"project_id\":" + PROJECT_ID + ",")
                            .append("\"section_id\":" + SECTION_ID + ",")
                            .append("\"order\":" + ORDER + ",")
                            .append("\"priority\":" + PRIORITY + ",")
                            .append("\"due_datetime\":\"" + DUE_DATETIME + "\"")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_403_Forbidden() throws Exception {
        System.out.println("Request with incorrect token");
        try {
            String result = sendPost(String.valueOf(uuid_same), String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\"")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_500_Internal_Server_Error() throws Exception {
        System.out.println("Task sucessfully created without unrequirement parameters");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"project_id\":" + 556)
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_Ingnored() throws Exception {
        System.out.println("UUID is already used");
        try {
            String result = sendPost(TOKEN, "295afb44-4g55-4735-819c-1e26b03e08ev",
                    json.append("{")
                    .append("\"content\":\"" + CONTENT + "\"")
                    .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_Bad_Request() throws Exception {
        System.out.println("Request body contains due_string and due_date");
        try {
            String result = sendPost(TOKEN, String.valueOf(uuid_same2),
                    json.append("{")
                            .append("\"content\":\"" + CONTENT + "\",")
                            .append("\"due_string\":\"" + DUE_STRING + "\",")
                            .append("\"due_date\":\"" + DUE_DATE + "\"")
                            .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_Emptycontent() throws Exception {
        System.out.println("There is no parameters in request body");
        try {
            String result = sendPost(TOKEN, uuid.toString(),
                    json.append("{")
                    .append("}"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_400_WrongTokenFormat() throws Exception {
        System.out.println("Without token");
        try {
            String result = sendPost("", uuid.toString(),
                    json.append("{")
                    .append("\"content\":\"Appointment\"")
                    .append("}"));
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

        post.setEntity(new StringEntity(requestBody.toString()));
        System.out.println(requestBody);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(response.getStatusLine().getStatusCode());
            result = EntityUtils.toString(response.getEntity());
                if (response.getStatusLine().getStatusCode() == 200){
                    JSONObject myObject = new JSONObject(result);
                    Assert.assertEquals(myObject.get("content"), CONTENT);
                    if (requestBody.toString().contains("project_id")) Assert.assertEquals(myObject.get("project_id"), PROJECT_ID);
                    if (requestBody.toString().contains("section_id")) Assert.assertEquals(myObject.get("section_id"), SECTION_ID);
                    if (requestBody.toString().contains("priority")) Assert.assertEquals(myObject.get("priority"), PRIORITY);
                    if (requestBody.toString().contains("order")) Assert.assertEquals(myObject.get("order"), ORDER);
                    if (requestBody.toString().contains("due_string")) {
                        Assert.assertTrue(!myObject.getJSONObject("due").isEmpty());
                        Assert.assertEquals(myObject.getJSONObject("due").getString("string"), DUE_STRING);
                    }
                    if (requestBody.toString().contains("due_date:")) {
                        Assert.assertTrue(!myObject.getJSONObject("due").isEmpty());
                        System.out.println("S:" + myObject.getJSONObject("due"));
                        Assert.assertEquals(myObject.getJSONObject("due").getString("string"), DUE_DATE);
                        Assert.assertEquals(myObject.getJSONObject("due").getString("date"), DUE_DATE);
                    }
                    if (requestBody.toString().contains("due_datetime")) {
                        Assert.assertTrue(!myObject.getJSONObject("due").isEmpty());
                        System.out.println("S:" + myObject.getJSONObject("due"));
                        Assert.assertEquals(myObject.getJSONObject("due").getString("datetime"), DUE_DATETIME.substring(0,19) + "Z");
                        Assert.assertEquals(myObject.getJSONObject("due").getString("date"), DUE_DATETIME.substring(0,10));
                        Assert.assertTrue(myObject.getJSONObject("due").has("timezone"));
                    }
            }
        }
        return "Method responses: " + result;
    }

    @AfterMethod
    public void close() throws IOException {
        httpClient.close();
    }
}
