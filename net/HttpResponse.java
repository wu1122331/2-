package cn.jiguang.net;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpResponse {
    private String a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private long e;
    private boolean f;
    private boolean g;
    private int h;

    public HttpResponse() {
        this.h = -1;
        this.c = new HashMap();
    }

    public HttpResponse(String str) {
        this.h = -1;
        this.a = str;
        this.d = 0;
        this.f = false;
        this.g = false;
        this.c = new HashMap();
    }

    private int a() {
        int indexOf;
        try {
            String str = (String) this.c.get("cache-control");
            if (TextUtils.isEmpty(str) || (indexOf = str.indexOf("max-age=")) == -1) {
                return -1;
            }
            int indexOf2 = str.indexOf(",", indexOf);
            int i = indexOf + 8;
            return Integer.parseInt(indexOf2 != -1 ? str.substring(i, indexOf2) : str.substring(i));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getExpiredTime() {
        if (this.g) {
            return this.e;
        }
        this.g = true;
        int a = a();
        long currentTimeMillis = a != -1 ? System.currentTimeMillis() + (a * 1000) : !TextUtils.isEmpty(getExpiresHeader()) ? HttpUtils.parseGmtTime(getExpiresHeader()) : -1L;
        this.e = currentTimeMillis;
        return currentTimeMillis;
    }

    public String getExpiresHeader() {
        try {
            Map<String, Object> map = this.c;
            if (map == null) {
                return null;
            }
            return (String) map.get("expires");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getResponseBody() {
        return this.b;
    }

    public int getResponseCode() {
        return this.h;
    }

    public int getType() {
        return this.d;
    }

    public String getUrl() {
        return this.a;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.e;
    }

    public boolean isInCache() {
        return this.f;
    }

    public void setExpiredTime(long j) {
        this.g = true;
        this.e = j;
    }

    public HttpResponse setInCache(boolean z) {
        this.f = z;
        return this;
    }

    public void setResponseBody(String str) {
        this.b = str;
    }

    public void setResponseCode(int i) {
        this.h = i;
    }

    public void setResponseHeader(String str, String str2) {
        Map<String, Object> map = this.c;
        if (map != null) {
            map.put(str, str2);
        }
    }

    public void setResponseHeaders(Map<String, Object> map) {
        this.c = map;
    }

    public void setType(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The type of HttpResponse cannot be smaller than 0.");
        }
        this.d = i;
    }

    public void setUrl(String str) {
        this.a = str;
    }

    public String toString() {
        return "HttpResponse{responseBody='" + this.b + "', responseCode=" + this.h + '}';
    }
}
