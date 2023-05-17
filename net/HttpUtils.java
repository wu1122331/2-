package cn.jiguang.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import cn.jiguang.ap.k;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpUtils {

    /* loaded from: classes.dex */
    public abstract class HttpListener {
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x0255, code lost:
        if (r12 != null) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0283, code lost:
        if (r12 == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0285, code lost:
        r12.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0201, code lost:
        if (r12 == null) goto L52;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013e A[Catch: all -> 0x0174, StackOverflowError -> 0x017a, Exception -> 0x017e, IOException -> 0x0184, MalformedURLException -> 0x018a, TryCatch #10 {IOException -> 0x0184, StackOverflowError -> 0x017a, MalformedURLException -> 0x018a, Exception -> 0x017e, all -> 0x0174, blocks: (B:43:0x010c, B:57:0x012e, B:59:0x0134, B:61:0x013e, B:63:0x014a), top: B:145:0x010c }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014a A[Catch: all -> 0x0174, StackOverflowError -> 0x017a, Exception -> 0x017e, IOException -> 0x0184, MalformedURLException -> 0x018a, TRY_LEAVE, TryCatch #10 {IOException -> 0x0184, StackOverflowError -> 0x017a, MalformedURLException -> 0x018a, Exception -> 0x017e, all -> 0x0174, blocks: (B:43:0x010c, B:57:0x012e, B:59:0x0134, B:61:0x013e, B:63:0x014a), top: B:145:0x010c }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016f  */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r12v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r12v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static cn.jiguang.net.HttpResponse a(android.content.Context r10, cn.jiguang.net.HttpRequest r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 661
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.net.HttpUtils.a(android.content.Context, cn.jiguang.net.HttpRequest, boolean):cn.jiguang.net.HttpResponse");
    }

    public static String appendParaToUrl(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        return sb.append(str2).append("=").append(str3).toString();
    }

    public static HttpURLConnection getHttpURLConnectionWithProxy(Context context, String str) {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        URL url = new URL(str);
        if (context != null) {
            try {
                if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0 && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.getType() != 1 && (extraInfo = activeNetworkInfo.getExtraInfo()) != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80)));
                }
            } catch (Throwable unused) {
            }
        }
        return (HttpURLConnection) url.openConnection();
    }

    public static String getUrlWithParas(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        String joinParas = joinParas(map);
        if (!TextUtils.isEmpty(joinParas)) {
            sb.append("?").append(joinParas);
        }
        return sb.toString();
    }

    public static String getUrlWithValueEncodeParas(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        String joinParasWithEncodedValue = joinParasWithEncodedValue(map);
        if (!TextUtils.isEmpty(joinParasWithEncodedValue)) {
            sb.append("?").append(joinParasWithEncodedValue);
        }
        return sb.toString();
    }

    public static HttpResponse httpGet(Context context, HttpRequest httpRequest) {
        return a(context, httpRequest, false);
    }

    public static HttpResponse httpGet(Context context, String str) {
        return httpGet(context, new HttpRequest(str));
    }

    public static void httpGet(Context context, HttpRequest httpRequest, HttpListener httpListener) {
        new a(context, httpListener).execute(httpRequest);
    }

    public static void httpGet(Context context, String str, HttpListener httpListener) {
        new b(httpListener, context).execute(str);
    }

    public static String httpGetString(Context context, HttpRequest httpRequest) {
        HttpResponse httpGet = httpGet(context, httpRequest);
        if (httpGet == null) {
            return null;
        }
        return httpGet.getResponseBody();
    }

    public static String httpGetString(Context context, String str) {
        HttpResponse httpGet = httpGet(context, new HttpRequest(str));
        if (httpGet == null) {
            return null;
        }
        return httpGet.getResponseBody();
    }

    public static HttpResponse httpPost(Context context, HttpRequest httpRequest) {
        return a(context, httpRequest, true);
    }

    public static HttpResponse httpPost(Context context, String str) {
        return httpPost(context, new HttpRequest(str));
    }

    public static String httpPostString(Context context, String str) {
        HttpResponse httpPost = httpPost(context, new HttpRequest(str));
        if (httpPost == null) {
            return null;
        }
        return httpPost.getResponseBody();
    }

    public static String httpPostString(Context context, String str, Map<String, String> map) {
        HttpResponse httpPost = httpPost(context, new HttpRequest(str, map));
        if (httpPost == null) {
            return null;
        }
        return httpPost.getResponseBody();
    }

    public static String joinParas(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            sb.append(next.getKey()).append("=").append(next.getValue());
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public static String joinParasWithEncodedValue(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("");
        if (map != null && map.size() > 0) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                try {
                    Map.Entry<String, String> next = it.next();
                    sb.append(next.getKey()).append("=").append(URLEncoder.encode(next.getValue(), "UTF-8"));
                    if (it.hasNext()) {
                        sb.append("&");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static long parseGmtTime(String str) {
        try {
            return cn.jiguang.as.b.a("EEE, d MMM yyyy HH:mm:ss z").parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static byte[] readInputStream(InputStream inputStream) {
        return k.b(inputStream);
    }

    public static void setURLConnection(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map == null || map.size() == 0 || httpURLConnection == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey())) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}
