package skd.requests;

import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import okhttp3.*;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class OkHttpLoggingInterceptor implements Interceptor {
    @Override
    public @NotNull Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        String reqMsg = String.format("--> Sending request %s %s on %s%n%s", request.method(), request.url(), chain.connection(), request.headers());
        System.out.println(reqMsg);

        BufferedSink requestBuffer = Okio.buffer(Okio.sink(new File(".cache", "okhttp-gzipped.log")));
        //            Buffer requestBuffer = new Buffer();
        RequestBody requestBody = request.body();
        if (requestBody != null) requestBody.writeTo(requestBuffer);
        String reqBody = requestBuffer.getBuffer().readUtf8();
        System.out.println(reqBody + "\n");

        Response response = chain.proceed(request);

        String filePath="";
        try {
            URI url = new URI(request.url().toString());
            //filePath = OkHttpGzippedLoggingInterceptor.getLogFile(url);
        } catch (URISyntaxException e) {
            filePath = "";
        }

        long t2 = System.nanoTime();
        String resMsg = String.format("<-- Received response for %s %s in %.1fms%n%s", response.request().url(), response.protocol().toString().toUpperCase(Locale.ROOT), (t2 - t1) / 1e6d, response.headers());
        System.out.println(resMsg);

        ResponseBody responseBody = response.body();
        MediaType contentType = null;
        String content = "NO CONTENT IN RESPONSE";
        if (responseBody != null) {
            contentType = responseBody.contentType();
            content = responseBody.string();
            System.out.println(content);
        }

        if (!filePath.isEmpty()) {
           // FileManager.save(filePath, String.format("%s%n%s%n%s%n%s%n", reqMsg, reqBody, resMsg, content));
        }

        ResponseBody wrappedBody = ResponseBody.create(contentType, content);
        return response.newBuilder().removeHeader("Content-Encoding").body(wrappedBody).build();
    }
}