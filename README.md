# Java Requests
Easy to use java request library.
This was earlier part of `https://github.com/sapandang/Load-Director` 

## Design
Aim is to provide simple syntax for sending requests.
For this all the methods are set as multi parameters.

## Add depedency

Select the version from the repo
* https://central.sonatype.com/artifact/io.github.sapandang/Jrequests


* Add the version in your build tool
* Gradle
```groovy
implementation 'io.github.sapandang:Jrequests:1.0'
```
* Maven
```xml
<dependency>
    <groupId>io.github.sapandang</groupId>
    <artifactId>Jrequests</artifactId>
    <version>1.0</version>
</dependency>
```



## Usage
### Get

```java
String BASE_URL="https://webhook.site/";

Requests requests = new Requests();
String url = BASE_URL+"/getTest";

Headers headers = new Headers();
headers.put("Content-Type","application/json");

ResponseData responseData =  requests.get(url,headers);
String resbody = responseData.body;
System.out.println(":GET: "+resbody);
```

### POST

```java
Requests requests = new Requests();
String url = BASE_URL+"/PostTest";
Headers headers = new Headers();
headers.put("Content-Type","application/json");
ResponseData responseData =  requests.postRaw(url,"this is the body to post",headers);
String resbody = responseData.body;
System.out.println(":POST: "+resbody);
```

### Multipart form body

```java

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
```

### Post File
```java
Requests requests = new Requests();
String url = BASE_URL+"/PostFile";
ResponseData fileRepose = requests.postfile(BASE_URL+"/postFile",
        "ThisIsFileKey",
        "File Name",
        new File("myfile"));

//send with media type
ResponseData fileRepose2 = requests.postfile(BASE_URL+"/postFile2",
        "application/zip",
        "ThisIsFileKey",
        "File Name",
        new File("a.zip"));
```

### Async Requests
Just add pass the call back to any method to make it async

```java
new AsyncResponseCallback() {
    @Override
    public void onResponse(ResponseData response) {

        System.out.println(":POST ASYNC: ");
    }
}
```

example:
```java
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
```


