package cn.jiguang.ab;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.as.i;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class h {
    private static Boolean a;

    private static String a(Context context) {
        if (context == null) {
            return "-1";
        }
        if (!b(context) || ((Integer) cn.jiguang.e.b.a(context, cn.jiguang.e.a.k())).intValue() == 1) {
            cn.jiguang.ac.d.b("LocalShareProcessHelper", "[getTypeJson]share process is close by action");
            return "-4";
        } else if (!c.a().d()) {
            cn.jiguang.ac.d.b("LocalShareProcessHelper", "getAttachJson,is not support jpush or jmessage ");
            return "-7";
        } else {
            int intValue = ((Integer) cn.jiguang.e.b.a(context, cn.jiguang.e.a.f())).intValue();
            if (intValue < 0) {
                cn.jiguang.ac.d.b("LocalShareProcessHelper", "[getTypeJson]idc<0,need login to get it");
                return "-3";
            }
            long longValue = ((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.c())).longValue();
            if (longValue <= 0) {
                cn.jiguang.ac.d.b("LocalShareProcessHelper", "[getTypeJson]uid<=0,need login to get it");
                return "-2";
            }
            String str = (String) cn.jiguang.e.b.a(context, cn.jiguang.e.a.i());
            long longValue2 = ((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.j())).longValue();
            String b = f.b(context);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("u", longValue);
                jSONObject.put("p", cn.jiguang.e.b.a(context, cn.jiguang.e.a.e()));
                jSONObject.put("ud", str);
                jSONObject.put("ak", b);
                jSONObject.put("idc", intValue);
                jSONObject.put("pn", context.getPackageName());
                jSONObject.put("sv", 212);
                jSONObject.put("uct", longValue2);
                return i.a(jSONObject.toString());
            } catch (JSONException unused) {
                cn.jiguang.ac.d.b("LocalShareProcessHelper", "[getTypeJson] to json error");
                return JCoreManager.SDK_VERSION;
            }
        }
    }

    public static String a(Context context, Uri uri) {
        String queryParameter;
        if (uri == null) {
            return JCoreManager.SDK_VERSION;
        }
        try {
            queryParameter = uri.getQueryParameter("kpgt");
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("LocalShareProcessHelper", "parseUriFromProvider failed:" + th.getMessage());
        }
        if (TextUtils.isEmpty(queryParameter)) {
            return JCoreManager.SDK_VERSION;
        }
        String b = i.b(queryParameter);
        if (TextUtils.isEmpty(b)) {
            return "-6";
        }
        JSONObject jSONObject = new JSONObject(b);
        String optString = jSONObject.optString("kta");
        cn.jiguang.ac.d.b("LocalShareProcessHelper", "action:" + optString);
        if (!TextUtils.isEmpty(optString)) {
            if (optString.equals("asai")) {
                return a(context);
            }
            if (optString.equals("asm")) {
                cn.jiguang.ac.d.b("LocalShareProcessHelper", "recv msg:" + jSONObject.toString());
                if (b(context) && ((Integer) cn.jiguang.e.b.a(context, cn.jiguang.e.a.k())).intValue() != 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("data", jSONObject.toString());
                    f.a(context, cn.jiguang.a.a.d, "asm", bundle);
                    return "0";
                }
                cn.jiguang.ac.d.b("LocalShareProcessHelper", "share process is closed!");
                return "-4";
            } else if (optString.equals("asmr")) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("data", jSONObject.toString());
                f.a(context, cn.jiguang.a.a.d, "asmr", bundle2);
            }
        }
        return JCoreManager.SDK_VERSION;
    }

    public static void a(Context context, Intent intent) {
        if (intent != null) {
            try {
                if ("asm".equals(intent.getAction())) {
                    f.a(context, cn.jiguang.a.a.d, "asm", intent.getExtras());
                    return;
                }
            } catch (Throwable th) {
                cn.jiguang.ac.d.f("LocalShareProcessHelper", "shareActionRun error:" + th.getMessage());
                return;
            }
        }
        cn.jiguang.ac.d.b("LocalShareProcessHelper", "shareActionRun intent error:" + (intent == null ? "null" : intent.getAction()));
    }

    private static boolean b(Context context) {
        Boolean bool = a;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            cn.jiguang.ac.d.f("LocalShareProcessHelper", "context is null");
            return true;
        }
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("cn.jiguang.android.share.close");
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            a = queryIntentServices == null || queryIntentServices.isEmpty();
            return a.booleanValue();
        } catch (Throwable unused) {
            cn.jiguang.ac.a.a("JCore", "LocalShareProcessHelper", false, 3, "Get isShareProcessModeOpen error#");
            return true;
        }
    }
}
