package cn.jiguang.aa;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class c {
    private static Boolean a;

    public static Bundle a(HashMap<String, String> hashMap) {
        if (hashMap.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (String str : hashMap.keySet()) {
            bundle.putString(str, hashMap.get(str));
        }
        return bundle;
    }

    public static String a(List<cn.jiguang.z.c> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).a;
            String str2 = list.get(i).b;
            int i2 = list.get(i).c;
            sb.append(str);
            sb.append("|");
            sb.append(str2);
            sb.append("|");
            sb.append(i2);
            sb.append("$");
        }
        return sb.toString();
    }

    public static List<cn.jiguang.z.c> a(Context context) {
        cn.jiguang.z.c a2;
        try {
            ArrayList arrayList = new ArrayList();
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent();
            intent.setAction("cn.jpush.android.intent.DaemonService");
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() != 0) {
                for (int i = 0; i < queryIntentServices.size(); i++) {
                    ServiceInfo serviceInfo = queryIntentServices.get(i).serviceInfo;
                    String str = serviceInfo.name;
                    String str2 = serviceInfo.packageName;
                    if (str != null && str2 != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && serviceInfo.exported && serviceInfo.enabled && !context.getPackageName().equals(str2) && (a2 = a.a(context, packageManager, str2, str)) != null) {
                        cn.jiguang.ai.a.c("JWakeHelper", "wakeTarget:" + a2.toString());
                        arrayList.add(a2);
                    }
                }
                return arrayList;
            }
            return null;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JWakeHelper", "getWakeTargetList throwable:" + th.getMessage());
            return null;
        }
    }

    public static List<String> a(cn.jiguang.z.a aVar, List<String> list) {
        if (aVar == null) {
            return list;
        }
        if (!TextUtils.isEmpty(aVar.h) && !aVar.h.equals("disable")) {
            List<String> list2 = aVar.i;
            String str = aVar.h;
            str.hashCode();
            if (str.equals(SocialConstants.PARAM_EXCLUDE)) {
                list = a(list2, list, false);
            } else if (str.equals("include")) {
                list = a(list2, list, true);
            }
        }
        return a(aVar.j, list, false);
    }

    public static List<cn.jiguang.z.c> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("\\$");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            String[] split2 = str2.split("\\|");
            String str3 = split2[0];
            String str4 = split2[1];
            int intValue = Integer.valueOf(split2[2]).intValue();
            cn.jiguang.z.c cVar = new cn.jiguang.z.c();
            cVar.a = str3;
            cVar.b = str4;
            cVar.c = intValue;
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private static List<String> a(List<String> list, List<String> list2, boolean z) {
        if (list == null || list.size() == 0) {
            return list2;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list2) {
            if (list.contains(str)) {
                if (z) {
                    cn.jiguang.ai.a.c("JWakeHelper", str + " in the white list");
                    arrayList.add(str);
                } else {
                    cn.jiguang.ai.a.c("JWakeHelper", str + " in the black list");
                }
            }
            if (!z) {
                cn.jiguang.ai.a.c("JWakeHelper", str + " not in the global black list");
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String b(HashMap<String, String> hashMap) {
        if (hashMap.isEmpty()) {
            return null;
        }
        Uri.Builder builder = new Uri.Builder();
        for (String str : hashMap.keySet()) {
            builder.appendQueryParameter(str, hashMap.get(str));
        }
        return builder.toString();
    }

    public static boolean b(Context context) {
        Boolean bool;
        try {
            bool = a;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JWakeHelper", "isUserReportEnable throwable:" + th.getMessage());
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            return true;
        }
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setAction("cn.jpush.android.WAKED_NOT_REPORT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        a = queryIntentServices == null || queryIntentServices.isEmpty();
        return a.booleanValue();
    }
}
