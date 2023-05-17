package cn.jiguang.ap;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes.dex */
public final class j {
    private static List<String> a;

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add("358673013795895");
        a.add("004999010640000");
        a.add("00000000000000");
        a.add("000000000000000");
    }

    public static String a(Context context) {
        String str = (String) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.i());
        if (!TextUtils.isEmpty(str)) {
            str = new String(Base64.decode(str, 2));
        }
        if (a(str)) {
            return str;
        }
        String b = b(context);
        if (!TextUtils.isEmpty(b)) {
            cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.i().a((cn.jiguang.ae.b<String>) Base64.encodeToString(b.getBytes(), 2)));
        }
        return b;
    }

    private static boolean a(String str) {
        if (h.h(str) && str.length() >= 10) {
            for (String str2 : a) {
                if (str.startsWith(str2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static String b(Context context) {
        try {
            String str = c.a(context).q;
            if (a(str)) {
                return str;
            }
            String str2 = c.a(context).i;
            if (!a(str2) || "9774d56d682e549c".equals(str2.toLowerCase(Locale.getDefault()))) {
                String g = cn.jiguang.sdk.impl.b.r(context) ? a.g(context) : "";
                if (!a(g) && (g = c(context)) == null) {
                    g = " ";
                }
                return a(g) ? g : "";
            }
            return str2;
        } catch (Exception e) {
            cn.jiguang.ai.a.b("UDIDUtils", "", e);
            String c = c(context);
            return a(c) ? c : "";
        }
    }

    private static String c(Context context) {
        cn.jiguang.ai.a.c("UDIDUtils", "Action:getSavedUuid");
        String str = (String) cn.jiguang.ae.c.b(context, cn.jiguang.ae.b.y());
        if (h.a(str)) {
            if (a.a()) {
                String str2 = (String) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.j());
                return TextUtils.isEmpty(str2) ? (Build.VERSION.SDK_INT < 23 || (a.a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && a.a(context, "android.permission.READ_EXTERNAL_STORAGE"))) ? d(context) : e(context) : str2;
            }
            return e(context);
        }
        return str;
    }

    private static String d(Context context) {
        String b = a.b();
        String str = b == null ? null : b + ".push_udid";
        File file = h.a(str) ? null : new File(str);
        String b2 = d.b(file);
        if (!TextUtils.isEmpty(b2)) {
            cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.j().a((cn.jiguang.ae.b<String>) b2));
            cn.jiguang.ai.a.e("UDIDUtils", "Got sdcard file saved udid - " + b2);
            return b2;
        }
        String k = h.k(UUID.nameUUIDFromBytes(new StringBuilder().append(System.currentTimeMillis()).toString().getBytes()).toString());
        cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.j().a((cn.jiguang.ae.b<String>) k));
        d.a(file, k);
        return k;
    }

    private static String e(Context context) {
        cn.jiguang.ae.b<String> y = cn.jiguang.ae.b.y();
        String str = (String) cn.jiguang.ae.c.b(context, y);
        if (str == null) {
            String uuid = UUID.randomUUID().toString();
            cn.jiguang.ae.c.a(context, y.a((cn.jiguang.ae.b<String>) uuid));
            return uuid;
        }
        return str;
    }
}
