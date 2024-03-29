import okhttp3.*;
import org.json.JSONArray;
import skd.requests.*;
import skd.requests.Headers;

import java.io.File;
import java.io.IOException;

public class Test {


    String BASE_URL="https://webhook.site/893c3b74-dc26-4100-a8e2-bc6527c06aba";


    @org.junit.jupiter.api.Test
    public void multipartform(){


        Requests requests = new Requests();

        Headers headers = new Headers();
        headers.put("Content-Type","application/json");
        String authUrl = BASE_URL+"/app/authentication";
        MultiPartFormBody authData = new MultiPartFormBody();
        authData.add("j_username", "myuser")
                .add("j_password", "5dfae4ace9e99af5235b184086c78c02b526d57ed7d5f13b34d71d4884292fea")
                .add("remember-me", "false")
                .add("submit", "Login");
        ResponseData responseData =  requests.postFormData(authUrl,authData,headers);
        System.out.println(":MULTIPART: "+responseData.body);
    }

    @org.junit.jupiter.api.Test
    public void get(){

        Requests requests = new Requests();
        String url = BASE_URL+"/getTest";
        Headers headers = new Headers();
        headers.put("Content-Type","application/json");
        ResponseData responseData =  requests.get(url,headers);
        String resbody = responseData.body;
        System.out.println(":GET: "+resbody);

    }

    @org.junit.jupiter.api.Test
    public void post(){
        Requests requests = new Requests();
        String url = BASE_URL+"/PostTest";
        Headers headers = new Headers();
        headers.put("Content-Type","application/json");
        ResponseData responseData =  requests.postRaw(url,"this is the body to post",headers);
        String resbody = responseData.body;
        System.out.println(":POST: "+resbody);
    }


    @org.junit.jupiter.api.Test
    public void postAsync(){
        Requests requests = new Requests();
        String url = BASE_URL+"/PostAsycTest";
        Headers headers = new Headers();
        headers.put("Content-Type","application/json");
        requests.postRaw(url, "this is the body to post", headers, new AsyncResponseCallback() {
            @Override
            public void onResponse(ResponseData response) {

                System.out.println(":POST ASYNC: ");
            }
        });

        System.out.println("ASYNC REQUEST SEND");

    }


    @org.junit.jupiter.api.Test
    public void postFile() throws Exception {
        Requests requests = new Requests();
        String url = BASE_URL+"/PostFile";
        ResponseData fileRepose = requests.postfile(BASE_URL+"/postFile",
                "ThisIsFileKey",
                "File Name",
                new File("README.md"));

        //send with media type
        ResponseData fileRepose2 = requests.postfile(BASE_URL+"/postFile2",
                "application/zip",
                "ThisIsFileKey",
                "File Name",
                new File("README.md"));
    }




    @org.junit.jupiter.api.Test
    public void getAsync(){
        Requests requests = new Requests();
        String url = BASE_URL+"/getAsycTest";
        Headers headers = new Headers();
        headers.put("Content-Type","application/json");
        requests.get(url, headers, new AsyncResponseCallback() {
            @Override
            public void onResponse(ResponseData response) {
                System.out.println(":GET ASYNC: "+response.body);
            }
        });

        System.out.println("ASYNC REQUEST SEND");

    }

    @org.junit.jupiter.api.Test
    public void okHttpAsync(){

        System.out.println("Sending async request");
         OkHttpClient client = new OkHttpClient();
         Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                System.out.println("async request failed");

                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                System.out.println("async request success");

                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    okhttp3.Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }
            }
        });

    }


    public static void main(String[] args) {
        Test test = new Test();
        test.getAsync();
        test.getAsync();
        test.getAsync();
        test.getAsync();
    }


}
