package cn.jiguang.r;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import cn.jiguang.ap.h;
import cn.jiguang.f.i;
import com.facebook.internal.ServerProtocol;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class d {
    private static final Object a = new Object();
    private static CookieManager h;
    private String b;
    private String c;
    private String d;
    private Context e;
    private int f = 0;
    private String g;

    public d(Context context) {
        String b = cn.jiguang.ap.a.b(context, this.b);
        String b2 = c.b(context, this.c);
        String c = cn.jiguang.ap.a.c(context, this.d);
        CookieManager cookieManager = new CookieManager();
        h = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(h);
        this.e = context;
        this.b = b;
        this.c = b2;
        this.d = c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:143:0x0280, code lost:
        if (r14 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02a8, code lost:
        if (r14 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02d0, code lost:
        if (r14 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02d2, code lost:
        r14.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02d5, code lost:
        r10 = r16;
        r11 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01cd, code lost:
        r13 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01cf, code lost:
        if (r15 == null) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01d1, code lost:
        r15.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01d5, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01d6, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0277 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x029f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private cn.jiguang.q.a a(android.content.Context r31, java.lang.String r32, int r33, long r34, boolean r36, java.io.File r37, java.lang.String r38) {
        /*
            Method dump skipped, instructions count: 818
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.r.d.a(android.content.Context, java.lang.String, int, long, boolean, java.io.File, java.lang.String):cn.jiguang.q.a");
    }

    private String a(int i) {
        String a2 = cn.jiguang.o.c.a(this.e, i);
        this.g = a2;
        return a2;
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        String b = cn.jiguang.ap.a.b(context, "");
        String b2 = c.b(context, "");
        return h.d(b + b2 + cn.jiguang.ap.a.c(context, ""));
    }

    private String a(String str) {
        String e = e(str);
        if (!TextUtils.isEmpty(e) && Patterns.PHONE.matcher(e).matches()) {
            return e;
        }
        return null;
    }

    private String a(String str, cn.jiguang.q.a aVar) {
        if (a(this.e, aVar)) {
            return c(str);
        }
        return null;
    }

    private String a(TreeMap<String, String> treeMap) {
        if (treeMap.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            sb.append((Object) entry.getValue());
        }
        return h.d(sb.toString() + cn.jiguang.o.c.c(this.e)).toUpperCase();
    }

    private static void a(Map<String, List<String>> map) {
        List<String> list = map.get("Set-Cookie");
        if (list != null) {
            for (String str : list) {
                h.getCookieStore().add(null, HttpCookie.parse(str).get(0));
            }
        }
    }

    private static boolean a(Context context, cn.jiguang.q.a aVar) {
        File a2;
        if (context == null || aVar == null || (a2 = i.a(context, "resp.raw")) == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder("");
        if (aVar.c != null && aVar.c.size() > 0) {
            for (Map.Entry<String, List<String>> entry : aVar.c.entrySet()) {
                if (entry.getKey() != null) {
                    sb.append(entry.getKey()).append(": ");
                }
                Iterator<String> it = entry.getValue().iterator();
                if (it.hasNext()) {
                    sb.append(it.next());
                    while (it.hasNext()) {
                        sb.append(", ").append(it.next());
                    }
                }
                sb.append("\n");
            }
        }
        sb.append("\r\n\r\n");
        if (!TextUtils.isEmpty(aVar.b)) {
            sb.append(aVar.b);
        }
        if (cn.jiguang.ap.d.a(a2, sb.toString())) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(a2);
            File a3 = i.a(context, "resp.zip");
            if (a3 == null) {
                return false;
            }
            if (a3 != null) {
                try {
                    if (a3.exists()) {
                        a3.delete();
                    }
                } catch (Throwable th) {
                    cn.jiguang.ai.a.g("JCommonFileHelper", "delete throwable:" + th.getMessage());
                }
            }
            try {
                cn.jiguang.s.b.a(arrayList, a3);
                return true;
            } catch (IOException e) {
                cn.jiguang.ai.a.g("JDevicePNWorker", "report phoneNumber exception:" + e.getMessage());
                return false;
            }
        }
        return false;
    }

    private boolean a(String str, String str2, String str3) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (!TextUtils.isEmpty(str)) {
            treeMap.put("imei", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            treeMap.put("iccid", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            treeMap.put("imsi", str3);
        }
        treeMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, cn.jiguang.o.c.a(this.e));
        treeMap.put("app_id", cn.jiguang.o.c.b(this.e));
        treeMap.put("req_time", cn.jiguang.o.a.a());
        treeMap.put("sign", a(treeMap));
        String str4 = "";
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            try {
                str4 = str4 + "&" + ((Object) entry.getKey()) + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        try {
            cn.jiguang.q.a a2 = a(this.e, this.g + "statistic/query?" + str4, 10, 30000L, false, null, null);
            if (a2.a != 200) {
                return false;
            }
            JSONObject b = b(a2.b);
            String str5 = null;
            if (b != null) {
                if (b.optInt("code", -1) != 200) {
                    return false;
                }
                str5 = a(b.optString("num"));
            } else if (a2.c != null || !TextUtils.isEmpty(a2.b)) {
                synchronized (a) {
                    this.f = 0;
                    try {
                        str5 = a(str4, a2);
                    } catch (Throwable unused2) {
                    }
                    this.e.deleteFile("resp.raw");
                    this.e.deleteFile("resp.zip");
                }
            }
            if (TextUtils.isEmpty(str5)) {
                return false;
            }
            d(str5);
            return true;
        } catch (Throwable unused3) {
            return false;
        }
    }

    private static JSONObject b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new JSONObject(str);
        } catch (Exception e) {
            cn.jiguang.ai.a.g("JDevicePNWorker", "report phoneNumber exception:" + e.getMessage());
            return null;
        }
    }

    private String c(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            cn.jiguang.q.a a2 = a(this.e, this.g + "statistic/query?" + str, 10, 30000L, false, i.a(this.e, "resp.zip"), "resp_data");
            if (a2.a != 200) {
                return null;
            }
            JSONObject b = b(a2.b);
            if (b != null) {
                if (b.optInt("code", -1) != 200) {
                    return null;
                }
                return a(b.optString("num"));
            } else if (!(a2.c == null && TextUtils.isEmpty(a2.b)) && (i = this.f) <= 4) {
                this.f = i + 1;
                try {
                    return a(str, a2);
                } catch (Throwable th) {
                    cn.jiguang.ai.a.g("JDevicePNWorker", "report phoneNumber throwable:" + th.getMessage());
                    return null;
                }
            } else {
                return null;
            }
        } catch (Throwable th2) {
            cn.jiguang.ai.a.g("JDevicePNWorker", "report phoneNumber throwable:" + th2.getMessage());
            return null;
        }
    }

    private void d(String str) {
        StringBuilder sb;
        String message;
        String a2;
        cn.jiguang.o.c.a(this.e, str);
        try {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("num", str);
                if (!TextUtils.isEmpty(this.b)) {
                    jSONObject.put("imei", this.b);
                }
                if (!TextUtils.isEmpty(this.d)) {
                    jSONObject.put("imsi", this.d);
                }
                if (!TextUtils.isEmpty(this.c)) {
                    jSONObject.put("iccid", this.c);
                }
                try {
                    a2 = cn.jiguang.al.a.a(jSONObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(a2)) {
                    return;
                }
                JSONObject jSONObject2 = new JSONObject();
                i.a(this.e, jSONObject2, "nb");
                jSONObject2.put("content", a2);
                cn.jiguang.ai.a.c("JDevicePNWorker", "collect success:" + jSONObject2);
                i.a(this.e, (Object) jSONObject2);
                cn.jiguang.o.c.a(this.e, false);
            } catch (JSONException e2) {
                sb = new StringBuilder("report phoneNumber exception:");
                message = e2.getMessage();
                cn.jiguang.ai.a.g("JDevicePNWorker", sb.append(message).toString());
            }
        } catch (Throwable th) {
            sb = new StringBuilder("report phoneNumber throwable:");
            message = th.getMessage();
            cn.jiguang.ai.a.g("JDevicePNWorker", sb.append(message).toString());
        }
    }

    private String e(String str) {
        try {
            return cn.jiguang.al.a.a(str, cn.jiguang.o.c.c(this.e).substring(0, 16));
        } catch (Exception e) {
            cn.jiguang.ai.a.g("JDevicePNWorker", "report phoneNumber exception:" + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x009d A[Catch: Exception -> 0x00db, TryCatch #0 {Exception -> 0x00db, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x0010, B:9:0x0019, B:11:0x0025, B:14:0x002e, B:15:0x0033, B:17:0x0042, B:19:0x004a, B:21:0x0052, B:23:0x005a, B:26:0x0063, B:28:0x006b, B:30:0x0073, B:33:0x007c, B:35:0x0084, B:37:0x008c, B:44:0x009d, B:46:0x00a8, B:51:0x00b7, B:53:0x00c4, B:55:0x00cc), top: B:61:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.r.d.a():void");
    }
}
