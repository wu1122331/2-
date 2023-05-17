package cn.jiguang.am;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import cn.jiguang.ah.i;
import cn.jiguang.ah.o;
import cn.jiguang.ak.c;
import cn.jiguang.ap.d;
import cn.jiguang.ap.h;
import cn.jiguang.api.JCoreManager;
import cn.jpush.android.service.DownloadProvider;
import com.facebook.appevents.UserDataStore;
import java.io.File;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a implements Runnable {
    private static final String b = ".jpush" + File.separator + ".shareinfo" + File.separator;
    private static final Object f = new Object();
    private static Boolean g;
    private static Boolean h;
    private static a j;
    Context a;
    private int c;
    private long[] d;
    private String e;
    private volatile boolean i;
    private boolean k;

    private a() {
        cn.jiguang.sdk.impl.b.a("share_process_executor");
    }

    private static ActivityInfo a(String str, Context context) {
        String str2;
        try {
            Intent intent = new Intent();
            intent.setAction("cn.jpush.android.intent.DownloadActivity");
            intent.addCategory(str);
            intent.setPackage(str);
            ActivityInfo activityInfo = context.getPackageManager().resolveActivity(intent, 0).activityInfo;
            if ((activityInfo instanceof ActivityInfo) && ((ComponentInfo) activityInfo).exported && ((ComponentInfo) activityInfo).enabled) {
                if (!"jpush.custom".equals(activityInfo.taskAffinity)) {
                    str2 = "download activity need config taskAffinity is jpush.custom";
                } else if (activityInfo.theme == 16973840) {
                    return activityInfo;
                } else {
                    str2 = "download activity theme must config as @android:style/Theme.Translucent.NoTitleBar";
                }
                cn.jiguang.ai.a.c("ShareProcessManager", str2);
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ShareProcessManager", "check downloadActivity error:" + th.getMessage());
        }
        cn.jiguang.ai.a.c("ShareProcessManager", "DownloadActivity is invalid in " + str);
        return null;
    }

    public static a a() {
        if (j == null) {
            synchronized (f) {
                if (j == null) {
                    j = new a();
                }
            }
        }
        return j;
    }

    private b a(String str) {
        Throwable th;
        b bVar;
        try {
        } catch (Throwable th2) {
            th = th2;
            bVar = null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        bVar = new b(this);
        try {
            long optLong = jSONObject.optLong("u");
            String optString = jSONObject.optString("ak");
            String optString2 = jSONObject.optString("pn");
            String optString3 = jSONObject.optString("ud");
            int optInt = jSONObject.optInt("idc", -1);
            int optInt2 = jSONObject.optInt("sv");
            long optLong2 = jSONObject.optLong("uct", -1L);
            bVar.d = optLong;
            bVar.c = optString3;
            bVar.f = optString;
            bVar.b = optInt;
            bVar.e = optString2;
            bVar.g = optInt2;
            bVar.h = optLong2;
        } catch (Throwable th3) {
            th = th3;
            cn.jiguang.ai.a.g("ShareProcessManager", "parse json to shareBean failed:" + th.getMessage());
            return bVar;
        }
        return bVar;
    }

    private static String a(Context context, String str, String str2, HashMap<String, String> hashMap) {
        try {
            ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            String str3 = str + ".DownloadProvider";
            if (!str3.startsWith("content://")) {
                str3 = "content://" + str3;
            }
            Uri parse = Uri.parse(str3);
            JSONObject jSONObject = new JSONObject();
            Uri.Builder buildUpon = parse.buildUpon();
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("kta", str2);
            }
            if (hashMap != null && !hashMap.isEmpty()) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
            buildUpon.appendQueryParameter("kpgt", cn.jiguang.al.a.a(jSONObject.toString()));
            return contentResolver.getType(buildUpon.build());
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ShareProcessManager", "callUriToDownloadProvider error:" + th.getMessage());
            return null;
        }
    }

    private static JSONObject a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ShareProcessManager", "mapToJSONObject error:" + th.getMessage());
        }
        return jSONObject;
    }

    public static void a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            cn.jiguang.ai.a.g("ShareProcessManager", "deletFileIfUninstall failed ,context is null or pkgname is empty");
            return;
        }
        try {
            if (cn.jiguang.ap.a.a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                File b2 = b(str);
                if (b2.exists()) {
                    b2.delete();
                } else {
                    cn.jiguang.ai.a.c("ShareProcessManager", "not found file in sdcard,filepath:" + b2.getAbsolutePath());
                }
            } else {
                cn.jiguang.ai.a.c("ShareProcessManager", "no write sdcard permission when deletFileIfUninstall");
            }
        } catch (Throwable unused) {
        }
    }

    private b b(Context context, String str) {
        String str2;
        File b2;
        String str3;
        String str4;
        String str5;
        String str6;
        try {
            String a = a(context, str, "asai", null);
            cn.jiguang.ai.a.c("ShareProcessManager", "get type from:" + str + ",info:" + a);
            if (a != null) {
                if (!TextUtils.isEmpty(a) && a.length() > 10) {
                    String b3 = cn.jiguang.al.a.b(a, "");
                    if (TextUtils.isEmpty(b3)) {
                        cn.jiguang.ai.a.c("ShareProcessManager", "decrypt error");
                        return null;
                    }
                    cn.jiguang.ai.a.c("ShareProcessManager", "parse success:" + b3);
                    return a(b3);
                }
                str2 = "is not shareprocessbean info";
            } else if (!f(context) || cn.jiguang.sdk.impl.b.a(context, false, "do not get share info from SD")) {
                return null;
            } else {
                if (cn.jiguang.ap.a.a(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                    String b4 = d.b(b(str));
                    if (TextUtils.isEmpty(b4)) {
                        str2 = "read info is empty from :" + b2.getAbsolutePath();
                    } else {
                        b a2 = a(cn.jiguang.al.a.b(b4, ""));
                        if (a2 != null) {
                            str3 = a2.e;
                            if (!cn.jiguang.ap.a.e(context, str3)) {
                                StringBuilder sb = new StringBuilder("found target app is uninsatll when scan sdcard,pkgname:");
                                str4 = a2.e;
                                cn.jiguang.ai.a.c("ShareProcessManager", sb.append(str4).toString());
                                str5 = a2.e;
                                a(context, str5);
                                return null;
                            }
                            str6 = a2.e;
                            if (a(str6, context) != null) {
                                cn.jiguang.ai.a.c("ShareProcessManager", "get share bean info from sdcard:" + a2.toString());
                                return a2;
                            }
                            str2 = "not config DownloadActivity in target app:" + str;
                        } else {
                            str2 = "parse share process bean with target app:" + str;
                        }
                    }
                } else {
                    str2 = "no read sdcard permission";
                }
            }
            cn.jiguang.ai.a.c("ShareProcessManager", str2);
            return null;
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("ShareProcessManager", "scanShareProcessBean error:" + th.getMessage());
            return null;
        }
    }

    private static File b(String str) {
        String d = h.d(str);
        if (!TextUtils.isEmpty(d)) {
            str = d;
        }
        return new File(Environment.getExternalStorageDirectory(), b + str);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00fa A[Catch: all -> 0x0183, TryCatch #0 {all -> 0x0183, blocks: (B:3:0x000d, B:5:0x0022, B:8:0x002a, B:9:0x0030, B:11:0x0036, B:13:0x0048, B:15:0x004e, B:17:0x0058, B:19:0x0060, B:21:0x0064, B:23:0x009c, B:25:0x00a0, B:27:0x00a8, B:33:0x00fa, B:30:0x00c5, B:34:0x00fd, B:35:0x0101, B:37:0x0121, B:38:0x0124, B:39:0x013e, B:41:0x0144, B:43:0x0162, B:44:0x0166, B:45:0x017d), top: B:50:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fd A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Set<cn.jiguang.am.b> c(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.am.a.c(android.content.Context):java.util.Set");
    }

    private void d(Context context) {
        try {
            if (cn.jiguang.sdk.impl.b.a(context, false, "do not save ShareInfo to SD")) {
                return;
            }
            if (!cn.jiguang.ap.a.a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                cn.jiguang.ai.a.c("ShareProcessManager", "no write sdcard permission");
                return;
            }
            File b2 = b(context.getPackageName());
            if (!f(context)) {
                d.a(b2);
                return;
            }
            String e = e(context);
            cn.jiguang.ai.a.c("ShareProcessManager", "save info to sdcard:" + b2.getAbsolutePath());
            if (TextUtils.isEmpty(e) || e.length() <= 10) {
                return;
            }
            d.a(b2);
            d.a(b2, e);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ShareProcessManager", "saveShareInfoToSdCard failed:" + th.getMessage());
        }
    }

    private String e(Context context) {
        if (context == null) {
            return "-1";
        }
        if (!g(context) || cn.jiguang.sdk.impl.b.m(context) == 1) {
            cn.jiguang.ai.a.c("ShareProcessManager", "[getTypeJson]share process is close by action");
            return "-4";
        } else if (!cn.jiguang.sdk.impl.b.q(context)) {
            cn.jiguang.ai.a.c("ShareProcessManager", "getAttachJson,is not support jpush or jmessage ");
            return "-7";
        } else {
            int n = cn.jiguang.sdk.impl.b.n(context);
            if (n < 0) {
                cn.jiguang.ai.a.c("ShareProcessManager", "[getTypeJson]idc<0,need login to get it");
                return "-3";
            }
            long e = cn.jiguang.sdk.impl.b.e(context);
            if (e <= 0) {
                cn.jiguang.ai.a.c("ShareProcessManager", "[getTypeJson]uid<=0,need login to get it");
                return "-2";
            }
            Map l = cn.jiguang.sdk.impl.b.l(context);
            long j2 = -1;
            if (l != null) {
                this.e = (String) l.get("uuid");
                j2 = ((Long) l.get(UserDataStore.CITY)).longValue();
            }
            String i = cn.jiguang.sdk.impl.b.i(context);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("u", e);
                jSONObject.put("p", cn.jiguang.sdk.impl.b.f(context));
                jSONObject.put("ud", this.e);
                jSONObject.put("ak", i);
                jSONObject.put("idc", n);
                jSONObject.put("pn", context.getPackageName());
                jSONObject.put("sv", 212);
                jSONObject.put("uct", j2);
                return cn.jiguang.al.a.a(jSONObject.toString());
            } catch (JSONException unused) {
                cn.jiguang.ai.a.c("ShareProcessManager", "[getTypeJson] to json error");
                return JCoreManager.SDK_VERSION;
            }
        }
    }

    private static boolean f(Context context) {
        Boolean bool = h;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            String str = Build.MANUFACTURER;
            String lowerCase = "Xiaomi".toLowerCase();
            if (!TextUtils.isEmpty(str) && TextUtils.equals(lowerCase, str.toLowerCase())) {
                cn.jiguang.ai.a.c("ShareProcessManager", "xiaomi not use activity and sdcard");
                Boolean bool2 = false;
                h = bool2;
                return bool2.booleanValue();
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ShareProcessManager", "get MANUFACTURER failed - error:" + th.getMessage());
        }
        h = a(context.getPackageName(), context) != null;
        return h.booleanValue();
    }

    private static boolean g(Context context) {
        Boolean bool = g;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            cn.jiguang.ai.a.g("ShareProcessManager", "context is null");
            return true;
        }
        try {
            ProviderInfo b2 = cn.jiguang.ap.a.b(context, context.getPackageName(), DownloadProvider.class);
            if (b2 == null) {
                cn.jiguang.ai.a.c("ShareProcessManager", "not found download provider in manifest");
                Boolean bool2 = false;
                g = bool2;
                return bool2.booleanValue();
            }
            if (((ComponentInfo) b2).enabled && ((ComponentInfo) b2).exported && !TextUtils.isEmpty(b2.authority)) {
                Intent intent = new Intent();
                intent.setPackage(context.getPackageName());
                intent.setAction("cn.jiguang.android.share.close");
                List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
                g = queryIntentServices == null || queryIntentServices.isEmpty();
                return g.booleanValue();
            }
            cn.jiguang.ai.a.c("ShareProcessManager", "download provider config error,enable" + ((ComponentInfo) b2).enabled + ",exported:" + ((ComponentInfo) b2).exported + ",authority:" + b2.authority);
            Boolean bool3 = false;
            g = bool3;
            return bool3.booleanValue();
        } catch (Throwable th) {
            cn.jiguang.ai.a.c("ShareProcessManager", "Get isShareProcessModeOpen error#:" + th.getMessage());
            return true;
        }
    }

    public final void a(int i, int i2) {
        cn.jiguang.ai.a.c("ShareProcessManager", "requestsuccess,cmd:" + i2 + ",code:0");
        if (i2 == 30) {
            this.c = 0;
        }
    }

    public final synchronized void a(Context context) {
        if (g(context) && cn.jiguang.sdk.impl.b.m(context) != 1) {
            if (!cn.jiguang.sdk.impl.b.q(context)) {
                cn.jiguang.ai.a.c("ShareProcessManager", "is not support jpush or jmessage ");
                return;
            }
            Object a = cn.jiguang.ah.d.a(context, "getwakeenable", null);
            if ((a instanceof Boolean) && !((Boolean) a).booleanValue()) {
                cn.jiguang.ai.a.c("ShareProcessManager", "wake disable,not scan share app");
                return;
            } else if (this.i) {
                cn.jiguang.ai.a.c("ShareProcessManager", "isAttaching");
                return;
            } else {
                this.a = context;
                this.i = true;
                cn.jiguang.ai.a.c("ShareProcessManager", "scanOtherApp...");
                this.c = 0;
                cn.jiguang.sdk.impl.b.a("share_process_executor", this);
                return;
            }
        }
        cn.jiguang.ai.a.c("ShareProcessManager", "share process is close by action");
        a(context, context.getPackageName());
    }

    public final void a(Context context, int i) {
        cn.jiguang.ai.a.c("ShareProcessManager", "requestTimeOut,cmd:" + i + ",isAttaching:" + this.i);
        if (i != 30 || this.i) {
            return;
        }
        this.c++;
        cn.jiguang.ai.a.c("ShareProcessManager", "attachTimeoutTimes:" + this.c + ",requestUIDS:" + this.d + ",shareProcessUUID:" + this.e);
        if (this.c > 2) {
            cn.jiguang.ai.a.c("ShareProcessManager", "attach too many times by once scan");
            return;
        }
        long[] jArr = this.d;
        if (jArr == null || jArr.length <= 0 || TextUtils.isEmpty(this.e)) {
            return;
        }
        cn.jiguang.ai.a.c("ShareProcessManager", "will retry attach");
        cn.jiguang.sdk.impl.b.a(context, cn.jiguang.sdk.impl.a.d, 30, 0, o.b(), 0L, cn.jiguang.ak.b.a(cn.jiguang.sdk.impl.b.e(context), this.e, this.d));
    }

    public final void a(Context context, long j2) {
        try {
            if (TextUtils.isEmpty(this.e)) {
                cn.jiguang.ai.a.c("ShareProcessManager", "dettachUid error,shareUUID is empty");
                return;
            }
            cn.jiguang.ai.a.c("ShareProcessManager", "dettach uid:" + j2);
            cn.jiguang.sdk.impl.b.a(context, cn.jiguang.sdk.impl.a.d, 32, 0, o.b(), 0L, cn.jiguang.ak.b.a(this.e, new long[]{j2}));
        } catch (Throwable th) {
            cn.jiguang.ai.a.c("ShareProcessManager", "dettach uid error:" + th.getMessage());
        }
    }

    public final void a(Context context, long j2, byte[] bArr) {
        long j3;
        if (j2 == 0 || bArr == null) {
            return;
        }
        try {
            Pair<c, ByteBuffer> a = cn.jiguang.ak.a.a(context, bArr, "");
            if (a != null && ((c) a.first).c == 3) {
                ByteBuffer byteBuffer = (ByteBuffer) a.second;
                byteBuffer.get();
                long j4 = byteBuffer.getLong();
                String a2 = cn.jiguang.ak.b.a(byteBuffer);
                if (TextUtils.isEmpty(a2)) {
                    cn.jiguang.ai.a.g("ShareProcessManager", "msgContent is empty");
                    return;
                }
                LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(a2));
                String readLine = lineNumberReader.readLine();
                if (TextUtils.isEmpty(readLine)) {
                    cn.jiguang.ai.a.i("ShareProcessManager", "appid is empty");
                    return;
                }
                String readLine2 = lineNumberReader.readLine();
                if (TextUtils.isEmpty(readLine2)) {
                    cn.jiguang.ai.a.i("ShareProcessManager", "senderId is empty");
                    return;
                }
                if (g(context) && cn.jiguang.sdk.impl.b.m(context) != 1) {
                    if (!cn.jiguang.ap.a.e(context, readLine)) {
                        a(context, ((c) a.first).g);
                        cn.jiguang.ai.a.c("ShareProcessManager", "app not installed:" + readLine);
                        a(context, readLine);
                        return;
                    }
                    String encodeToString = Base64.encodeToString(bArr, 10);
                    Long valueOf = Long.valueOf(((c) a.first).e);
                    HashMap hashMap = new HashMap();
                    hashMap.put("ktm", encodeToString);
                    hashMap.put("ktp", cn.jiguang.al.a.a(cn.jiguang.sdk.impl.b.e(context)));
                    hashMap.put("ktma", readLine2);
                    hashMap.put("mtmmi", new StringBuilder().append(j4).toString());
                    hashMap.put("ktmfp", context.getPackageName());
                    hashMap.put("ktmr", new StringBuilder().append(valueOf).toString());
                    cn.jiguang.ai.a.c("ShareProcessManager", "dispatch share msg,appkey:" + readLine2 + ",msgid:" + j4 + ",rid:" + valueOf);
                    String a3 = a(context, readLine, "asm", hashMap);
                    cn.jiguang.ai.a.c("ShareProcessManager", "dispatch result:" + a3);
                    if (TextUtils.isEmpty(a3)) {
                        if (!f(context)) {
                            cn.jiguang.ai.a.c("ShareProcessManager", "app can not use downloadActivity dispatch msg");
                            j3 = ((c) a.first).g;
                        } else if (!cn.jiguang.sdk.impl.b.a && cn.jiguang.sdk.impl.b.a(context, false, "do not startActivity in BackGround")) {
                            this.k = true;
                            a(context, cn.jiguang.sdk.impl.b.e(context));
                            return;
                        } else {
                            ActivityInfo a4 = a(readLine, context);
                            if (a4 != null) {
                                cn.jiguang.ai.a.c("ShareProcessManager", "will try use downloadActivity");
                                JSONObject a5 = a(hashMap);
                                Intent intent = new Intent("asm");
                                intent.setComponent(new ComponentName(a4.packageName, a4.name));
                                intent.setFlags(268435456);
                                intent.addCategory(readLine);
                                intent.putExtra("data", a5.toString());
                                context.startActivity(intent);
                            } else {
                                j3 = ((c) a.first).g;
                            }
                        }
                        a(context, j3);
                    } else if (a3.equals("-4")) {
                        j3 = ((c) a.first).g;
                        a(context, j3);
                    } else {
                        cn.jiguang.ai.a.c("ShareProcessManager", a3.equals("0") ? "wait the msg reponse" : "provider is :" + a3 + ",app is less than jcore_v125");
                    }
                    lineNumberReader.close();
                    return;
                }
                a(context, cn.jiguang.sdk.impl.b.e(context));
                cn.jiguang.ai.a.c("ShareProcessManager", " share process is close,will not dispatch the msg and dettach mine uid");
                return;
            }
            cn.jiguang.ai.a.c("ShareProcessManager", "share msg cmd is not 3");
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ShareProcessManager", "dispatchMsg error:" + th.getMessage());
        }
    }

    public final void a(Context context, Bundle bundle) {
        String str;
        try {
            cn.jiguang.ai.a.c("ShareProcessManager", "doMsg");
            if (bundle != null) {
                String string = bundle.getString("data");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(string);
                cn.jiguang.ai.a.c("ShareProcessManager", "doMsg json:" + jSONObject.toString());
                String optString = jSONObject.optString("ktm");
                String optString2 = jSONObject.optString("ktp");
                String optString3 = jSONObject.optString("mtmmi");
                String optString4 = jSONObject.optString("ktmfp");
                String optString5 = jSONObject.optString("ktma");
                String optString6 = jSONObject.optString("ktmr");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString3) || TextUtils.isEmpty(optString4) || TextUtils.isEmpty(optString5)) {
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("mtmmi", optString3);
                hashMap.put("ktmfp", optString4);
                hashMap.put("ktma", optString5);
                hashMap.put("ktmr", optString6);
                Pair<c, ByteBuffer> a = cn.jiguang.ak.a.a(context, Base64.decode(optString, 10), optString2);
                if (a != null) {
                    hashMap.put("ktmu", new StringBuilder().append(((c) a.first).g).toString());
                    if (g(context) && cn.jiguang.sdk.impl.b.m(context) != 1) {
                        if (((c) a.first).g != cn.jiguang.sdk.impl.b.e(context)) {
                            cn.jiguang.ai.a.c("ShareProcessManager", "this msg uid is :" + ((c) a.first).g + ",is not this app msg");
                            str = "1";
                            hashMap.put("asmrc", str);
                        } else {
                            hashMap.put("asmrc", "0");
                            cn.jiguang.ah.b.a();
                            cn.jiguang.ah.b.a(context, (c) a.first, (ByteBuffer) a.second);
                        }
                    }
                    cn.jiguang.ai.a.c("ShareProcessManager", "share process is closed");
                    str = "3";
                    hashMap.put("asmrc", str);
                }
                a(context, optString4, "asmr", hashMap);
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.c("ShareProcessManager", "doMsg error:" + th.getMessage());
        }
    }

    public final void b(Context context) {
        if (this.k) {
            this.k = false;
            if (i.a().d()) {
                cn.jiguang.ai.a.c("ShareProcessManager", "attach mine while app in foreground from background");
                cn.jiguang.sdk.impl.b.a(context, cn.jiguang.sdk.impl.a.d, 30, 0, o.b(), 0L, cn.jiguang.ak.b.a(cn.jiguang.sdk.impl.b.e(context), this.e, new long[]{cn.jiguang.sdk.impl.b.e(context)}));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a7 A[Catch: all -> 0x027d, TRY_ENTER, TryCatch #1 {all -> 0x027d, blocks: (B:3:0x0005, B:5:0x000d, B:7:0x0026, B:14:0x008e, B:17:0x009c, B:21:0x00a7, B:22:0x00c8, B:24:0x00ce, B:26:0x00da, B:28:0x00e2, B:30:0x00f2, B:31:0x00f6, B:34:0x00ff, B:36:0x010e, B:38:0x0114, B:40:0x0169, B:42:0x0177, B:48:0x01be, B:46:0x018b, B:49:0x01c2, B:54:0x0200, B:55:0x0205, B:64:0x023c, B:58:0x0211, B:62:0x0234, B:59:0x021e, B:61:0x0226, B:63:0x0237, B:13:0x0055), top: B:75:0x0005 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 668
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.am.a.run():void");
    }
}
