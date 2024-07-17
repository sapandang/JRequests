package skd.requests;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Cookieejar implements  okhttp3.CookieJar{

    static HashMap<String,List<Cookie>> cookieeMap = new HashMap<>();

    static List<Cookie> cookies = new ArrayList<Cookie>();

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {

        System.out.println("COESEND cookieee "+cookies.size());
        return  cookies;
//
//        System.out.println(":COOKIEE: Loading cookiee for "+httpUrl.host());
//        List<Cookie> list = cookieeMap.get(httpUrl.host());
//        if (list == null)
//        {
//            return  list = new ArrayList<Cookie>();
//        }else {
//         return list;
//        }
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {

        List<Cookie> newList = new ArrayList<Cookie>(cookies);
        newList.addAll(list);
        cookies= newList;
//        cookies = Stream.concat(list.stream(), list.stream()).toList();


//        System.out.println(":COOKIEE: Saving cookiee for "+httpUrl.host()+" --- "+list.size());
        cookieeMap.put(httpUrl.host(),list);
    }


}
