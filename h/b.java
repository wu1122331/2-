package cn.jiguang.h;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import cn.jiguang.f.i;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b extends cn.jiguang.f.a {
    private static volatile b d;
    private Context a;
    private Set<String> b;
    private List<cn.jiguang.i.a> c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(b bVar, Context context, Intent intent) {
        String dataString;
        int i;
        String str;
        String str2;
        Set<String> set;
        String action = intent.getAction();
        if (action == null || TextUtils.isEmpty(action)) {
            cn.jiguang.ai.a.g("JAppMovement", "the action'" + action + "'is illegal");
        } else if ((action.equals("android.intent.action.PACKAGE_ADDED") || action.equals("android.intent.action.PACKAGE_REMOVED")) && (dataString = intent.getDataString()) != null && !TextUtils.isEmpty(dataString) && dataString.length() > 8 && dataString.startsWith("package:")) {
            String substring = dataString.substring(8);
            cn.jiguang.ai.a.c("JAppMovement", "receive the action'" + action + ",package:" + substring);
            boolean z = false;
            if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                i = cn.jiguang.j.a.a(cn.jiguang.j.a.a(context, substring));
                cn.jiguang.ai.a.c("JAppMovement", "report add app:" + substring);
                str = "add";
            } else {
                cn.jiguang.ai.a.c("JAppMovement", "report remove app:" + substring);
                i = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                str = "rmv";
            }
            bVar.a(substring, i, str, 0);
            Set<String> e = bVar.e();
            bVar.b = e;
            if (e != null && !e.isEmpty()) {
                try {
                    boolean z2 = true;
                    if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                        bVar.b.add(substring);
                        z = true;
                    }
                    if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                        bVar.b.remove(substring);
                    } else {
                        z2 = z;
                    }
                    if (z2 && (set = bVar.b) != null) {
                        String a = cn.jiguang.j.a.a(set);
                        if (!TextUtils.isEmpty(a)) {
                            cn.jiguang.ai.a.c("JAppMovement", "update installedAppList cache:" + bVar.b);
                            cn.jiguang.s.b.a(context, "bal.catch", a);
                        }
                    }
                } catch (Throwable th) {
                    str2 = "cache appList add remove failed:" + th.getMessage();
                }
                cn.jiguang.ai.a.c("JAppMovement", "executeAction: [JAppMovement]");
                bVar.c(context, "JAppMovement");
                bVar.d(context, "JAppMovement");
            }
            str2 = "get cache appList failed";
            cn.jiguang.ai.a.g("JAppMovement", str2);
            cn.jiguang.ai.a.c("JAppMovement", "executeAction: [JAppMovement]");
            bVar.c(context, "JAppMovement");
            bVar.d(context, "JAppMovement");
        }
    }

    private void a(String str, int i, String str2, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", str2);
            jSONObject.put("appid", str);
            jSONObject.put("source", i2);
            if (i != -1000) {
                jSONObject.put("install_type", i);
            }
            i.a(this.a, jSONObject, "app_add_rmv");
            i.a(this.a, (Object) jSONObject);
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JAppMovement", "package json exception:" + e.getMessage());
        }
    }

    private static boolean a(int i, String str) {
        if (i == 1) {
            cn.jiguang.ai.a.g("JAppMovement", "the " + str + " app is system app,need dealAction all apps,to executeMovementAction JAppAll");
            return true;
        }
        return false;
    }

    public static b d() {
        if (d == null) {
            synchronized (b.class) {
                d = new b();
            }
        }
        return d;
    }

    private Set<String> e() {
        String b = cn.jiguang.s.b.b(this.a, "bal.catch");
        if (TextUtils.isEmpty(b) || b == null || TextUtils.isEmpty(b)) {
            return null;
        }
        return cn.jiguang.j.a.a(b);
    }

    public final void a(Context context, Intent intent) {
        this.a = context;
        cn.jiguang.ai.a.c("JAppMovement", "executeMovementAction: [JAppMovement] from broadcast");
        if (a()) {
            cn.jiguang.sdk.impl.b.a("JCommon", new c(this, context, intent));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean a() {
        cn.jiguang.ai.a.c("JAppMovement", "for googlePlay:false");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        List<cn.jiguang.i.a> a = cn.jiguang.j.a.a(context, true);
        if (a == null || a.isEmpty()) {
            cn.jiguang.ai.a.g("JAppMovement", "collect installedAppList failed");
            return;
        }
        cn.jiguang.ai.a.c("JAppMovement", "collect installedAppList success");
        if (a.size() == 1 && a.get(0).b.equals(context.getPackageName())) {
            cn.jiguang.ai.a.g("JAppMovement", "installedAppList only has one app and this app is itself");
            return;
        }
        if (this.b == null) {
            this.b = e();
        }
        Set<String> set = this.b;
        if (set == null || set.isEmpty()) {
            cn.jiguang.ai.a.g("JAppMovement", "current appList cache is empty,need collect appList first,to executeMovementAction JAppAll");
            a.d().b(context);
            return;
        }
        cn.jiguang.ai.a.c("JAppMovement", "get installedAppList cache:" + this.b);
        this.c = new ArrayList(a);
        for (cn.jiguang.i.a aVar : a) {
            if (this.b.remove(aVar.b)) {
                this.c.remove(aVar);
            }
        }
        if (this.b.isEmpty() && this.c.isEmpty()) {
            cn.jiguang.ai.a.c("JAppMovement", "installedAppList has no change");
            return;
        }
        String a2 = cn.jiguang.j.a.a(a);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        cn.jiguang.ai.a.c("JAppMovement", "update installedAppList cache:" + a);
        cn.jiguang.s.b.a(context, "bal.catch", a2);
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JAppMovement";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        List<cn.jiguang.i.a> list = this.c;
        if (list == null || list.isEmpty()) {
            cn.jiguang.ai.a.g("JAppMovement", "there are no add app data to report");
        } else {
            for (cn.jiguang.i.a aVar : this.c) {
                if (a(aVar.e, "add")) {
                    a.d().b(context);
                } else {
                    a(aVar.b, aVar.e, "add", 1);
                    super.d(context, str);
                }
            }
        }
        Set<String> set = this.b;
        if (set == null || set.isEmpty()) {
            cn.jiguang.ai.a.g("JAppMovement", "there are no remove app data to report");
        } else {
            for (String str2 : this.b) {
                if (a((int) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, "rmv")) {
                    a.d().b(context);
                } else {
                    a(str2, NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, "rmv", 1);
                    super.d(context, str);
                }
            }
        }
        this.c = null;
        this.b = null;
    }
}
