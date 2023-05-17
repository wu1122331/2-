package cn.jiguang.h;

import android.content.Context;
import cn.jiguang.f.f;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e extends cn.jiguang.f.a {
    private static volatile e d;
    private Context a;
    private List<cn.jiguang.i.a> b;
    private List<cn.jiguang.i.b> c;

    public static e d() {
        if (d == null) {
            synchronized (e.class) {
                d = new e();
            }
        }
        return d;
    }

    @Override // cn.jiguang.f.a
    protected final void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("content");
        try {
            long j = optJSONObject.getLong("interval");
            if (j == -1) {
                f.a(this.a, "JAppRunning", false);
                return;
            }
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i == 0) {
                f.a(this.a, "JAppRunning", true);
                return;
            }
            int optInt = optJSONObject.optInt("app_type", 0);
            int optInt2 = optJSONObject.optInt("process_type", 0);
            f.a(this.a, "JAppRunning", true);
            f.a(this.a, optInt);
            f.b(this.a, optInt2);
            if (i > 0) {
                f.b(this.a, "JAppRunning", j);
            }
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JAppRunning", "parse interval exception:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean a() {
        cn.jiguang.ai.a.c("JAppRunning", "for googlePlay:false");
        return true;
    }

    @Override // cn.jiguang.f.a
    protected final boolean b() {
        return f.h(this.a, "JAppRunning");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        int a = f.a(context);
        int b = f.b(context);
        if (a > 0) {
            this.b = cn.jiguang.j.a.a(context);
        } else {
            cn.jiguang.ai.a.g("JAppRunning", "can't collect runningApp because reportRunningAppType:" + a);
        }
        if (b > 0) {
            this.c = cn.jiguang.j.b.a(b);
        } else {
            cn.jiguang.ai.a.g("JAppRunning", "can't collect runningProcess because reportRunningProcessType:" + b);
        }
        List<cn.jiguang.i.a> list = this.b;
        if (list != null && !list.isEmpty()) {
            cn.jiguang.ai.a.c("JAppRunning", "collect runningAppList success");
        }
        List<cn.jiguang.i.b> list2 = this.c;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        cn.jiguang.ai.a.c("JAppRunning", "collect runningProcessList success");
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JAppRunning";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    @Override // cn.jiguang.f.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(android.content.Context r8, java.lang.String r9) {
        /*
            r7 = this;
            java.util.List<cn.jiguang.i.a> r0 = r7.b
            r1 = 128(0x80, float:1.794E-43)
            java.lang.String r2 = "JAppRunning"
            r3 = 0
            if (r0 == 0) goto L30
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L30
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.util.List<cn.jiguang.i.a> r4 = r7.b
            java.util.Iterator r4 = r4.iterator()
        L1a:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L36
            java.lang.Object r5 = r4.next()
            cn.jiguang.i.a r5 = (cn.jiguang.i.a) r5
            org.json.JSONObject r5 = r5.a(r1)
            if (r5 == 0) goto L1a
            r0.put(r5)
            goto L1a
        L30:
            java.lang.String r0 = "there are no running app to report"
            cn.jiguang.ai.a.g(r2, r0)
            r0 = r3
        L36:
            java.util.List<cn.jiguang.i.b> r4 = r7.c
            if (r4 == 0) goto L61
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L61
            org.json.JSONArray r4 = new org.json.JSONArray
            r4.<init>()
            java.util.List<cn.jiguang.i.b> r5 = r7.c
            java.util.Iterator r5 = r5.iterator()
        L4b:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L67
            java.lang.Object r6 = r5.next()
            cn.jiguang.i.b r6 = (cn.jiguang.i.b) r6
            org.json.JSONObject r6 = r6.a(r1)
            if (r6 == 0) goto L4b
            r4.put(r6)
            goto L4b
        L61:
            java.lang.String r1 = "there are no running process to report"
            cn.jiguang.ai.a.g(r2, r1)
            r4 = r3
        L67:
            r7.b = r3
            r7.c = r3
            r1 = 0
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L92
            r5.<init>()     // Catch: org.json.JSONException -> L92
            r3 = 1
            if (r0 == 0) goto L84
            int r6 = r0.length()     // Catch: org.json.JSONException -> L81
            if (r6 <= 0) goto L84
            java.lang.String r6 = "app"
            r5.put(r6, r0)     // Catch: org.json.JSONException -> L81
            r1 = 1
            goto L84
        L81:
            r0 = move-exception
            r3 = r5
            goto L93
        L84:
            if (r4 == 0) goto Laa
            int r0 = r4.length()     // Catch: org.json.JSONException -> L81
            if (r0 <= 0) goto Laa
            java.lang.String r0 = "process"
            r5.put(r0, r4)     // Catch: org.json.JSONException -> L81
            goto Lab
        L92:
            r0 = move-exception
        L93:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "package json exception:"
            r4.<init>(r5)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r0 = r0.toString()
            cn.jiguang.ai.a.g(r2, r0)
            r5 = r3
        Laa:
            r3 = r1
        Lab:
            if (r3 == 0) goto Lb8
            java.lang.String r0 = "app_running"
            cn.jiguang.f.i.a(r8, r5, r0)
            cn.jiguang.f.i.a(r8, r5)
            super.d(r8, r9)
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.h.e.d(android.content.Context, java.lang.String):void");
    }
}
