package cn.jiguang.n;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.ap.h;
import cn.jiguang.f.f;
import cn.jiguang.f.i;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends cn.jiguang.f.a {
    private static volatile a c;
    private Context a;
    private JSONObject b;
    private String d;

    public static a d() {
        if (c == null) {
            synchronized (a.class) {
                c = new a();
            }
        }
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x021c A[Catch: all -> 0x031f, JSONException -> 0x0321, TryCatch #4 {JSONException -> 0x0321, all -> 0x031f, blocks: (B:13:0x0087, B:15:0x00a7, B:16:0x00b4, B:21:0x00d3, B:23:0x00fd, B:80:0x0229, B:83:0x023c, B:86:0x0262, B:89:0x026f, B:92:0x027c, B:95:0x0289, B:98:0x0296, B:102:0x02a6, B:106:0x02b6, B:110:0x02c6, B:114:0x02d6, B:118:0x02e6, B:122:0x02f6, B:126:0x0306, B:130:0x0316, B:28:0x010b, B:29:0x0112, B:31:0x0122, B:74:0x0216, B:76:0x021c, B:32:0x0128, B:35:0x0138, B:38:0x0147, B:41:0x0157, B:44:0x0167, B:47:0x0177, B:50:0x0187, B:52:0x0196, B:53:0x019c, B:56:0x01ad, B:59:0x01be, B:62:0x01cf, B:65:0x01e0, B:68:0x01f1, B:71:0x0202, B:73:0x0212, B:19:0x00bd, B:20:0x00c9), top: B:144:0x0087 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0294  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.json.JSONObject g(android.content.Context r28) {
        /*
            Method dump skipped, instructions count: 856
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.n.a.g(android.content.Context):org.json.JSONObject");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c A[Catch: all -> 0x00a8, TryCatch #0 {all -> 0x00a8, blocks: (B:3:0x0007, B:18:0x0048, B:21:0x005e, B:24:0x006b, B:27:0x0078, B:30:0x0085, B:33:0x0092, B:37:0x00a0, B:17:0x0042, B:14:0x003c, B:11:0x0021), top: B:42:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042 A[Catch: all -> 0x00a8, TryCatch #0 {all -> 0x00a8, blocks: (B:3:0x0007, B:18:0x0048, B:21:0x005e, B:24:0x006b, B:27:0x0078, B:30:0x0085, B:33:0x0092, B:37:0x00a0, B:17:0x0042, B:14:0x003c, B:11:0x0021), top: B:42:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String h(android.content.Context r11) {
        /*
            java.lang.String r0 = "JDevice"
            java.lang.String r1 = ","
            java.lang.String r2 = ""
            r3 = 0
            java.lang.String r4 = cn.jiguang.sdk.impl.b.d(r11)     // Catch: java.lang.Throwable -> La8
            java.lang.String r5 = cn.jiguang.sdk.impl.b.i(r11)     // Catch: java.lang.Throwable -> La8
            java.lang.String r6 = r11.getPackageName()     // Catch: java.lang.Throwable -> L1f
            android.content.pm.PackageManager r11 = r11.getPackageManager()     // Catch: java.lang.Throwable -> L1d
            r7 = 0
            android.content.pm.PackageInfo r11 = r11.getPackageInfo(r6, r7)     // Catch: java.lang.Throwable -> L1d
            goto L38
        L1d:
            r11 = move-exception
            goto L21
        L1f:
            r11 = move-exception
            r6 = r2
        L21:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8
            java.lang.String r8 = "getPackageManager failed:"
            r7.<init>(r8)     // Catch: java.lang.Throwable -> La8
            java.lang.String r11 = r11.getMessage()     // Catch: java.lang.Throwable -> La8
            java.lang.StringBuilder r11 = r7.append(r11)     // Catch: java.lang.Throwable -> La8
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> La8
            cn.jiguang.ai.a.g(r0, r11)     // Catch: java.lang.Throwable -> La8
            r11 = r3
        L38:
            if (r11 != 0) goto L3c
            r7 = r2
            goto L3e
        L3c:
            java.lang.String r7 = r11.versionName     // Catch: java.lang.Throwable -> La8
        L3e:
            if (r11 != 0) goto L42
            r11 = r2
            goto L48
        L42:
            int r11 = r11.versionCode     // Catch: java.lang.Throwable -> La8
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> La8
        L48:
            java.lang.String r8 = "2.1.2"
            java.lang.String r9 = "212"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8
            r10.<init>()     // Catch: java.lang.Throwable -> La8
            r10.append(r4)     // Catch: java.lang.Throwable -> La8
            r10.append(r1)     // Catch: java.lang.Throwable -> La8
            boolean r4 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> La8
            if (r4 == 0) goto L5e
            r5 = r2
        L5e:
            r10.append(r5)     // Catch: java.lang.Throwable -> La8
            r10.append(r1)     // Catch: java.lang.Throwable -> La8
            boolean r4 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> La8
            if (r4 == 0) goto L6b
            r7 = r2
        L6b:
            r10.append(r7)     // Catch: java.lang.Throwable -> La8
            r10.append(r1)     // Catch: java.lang.Throwable -> La8
            boolean r4 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> La8
            if (r4 == 0) goto L78
            r11 = r2
        L78:
            r10.append(r11)     // Catch: java.lang.Throwable -> La8
            r10.append(r1)     // Catch: java.lang.Throwable -> La8
            boolean r11 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> La8
            if (r11 == 0) goto L85
            r8 = r2
        L85:
            r10.append(r8)     // Catch: java.lang.Throwable -> La8
            r10.append(r1)     // Catch: java.lang.Throwable -> La8
            boolean r11 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> La8
            if (r11 == 0) goto L92
            r9 = r2
        L92:
            r10.append(r9)     // Catch: java.lang.Throwable -> La8
            r10.append(r1)     // Catch: java.lang.Throwable -> La8
            boolean r11 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> La8
            if (r11 == 0) goto L9f
            goto La0
        L9f:
            r2 = r6
        La0:
            r10.append(r2)     // Catch: java.lang.Throwable -> La8
            java.lang.String r11 = r10.toString()     // Catch: java.lang.Throwable -> La8
            return r11
        La8:
            r11 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getCurrentCondition throwable: "
            r1.<init>(r2)
            java.lang.String r11 = r11.getMessage()
            java.lang.StringBuilder r11 = r1.append(r11)
            java.lang.String r11 = r11.toString()
            cn.jiguang.ai.a.g(r0, r11)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.n.a.h(android.content.Context):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean b(Context context, String str) {
        if (f.b(context, str)) {
            JSONObject jSONObject = this.b;
            if (jSONObject == null) {
                cn.jiguang.ai.a.g("JDevice", "there are no data to report");
                return false;
            }
            String jSONObject2 = jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                return false;
            }
            this.d = h.k(jSONObject2 + h(context));
            String f = f.f(context);
            if (TextUtils.isEmpty(this.d) || TextUtils.equals(this.d, f)) {
                cn.jiguang.ai.a.c("JDevice", "device detail is not change");
                return false;
            }
            cn.jiguang.ai.a.c("JDevice", "device detail is change");
            return super.b(context, str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        JSONObject g = g(context);
        this.b = g;
        if (g != null) {
            cn.jiguang.ai.a.c("JDevice", "collect success:" + this.b);
        } else {
            cn.jiguang.ai.a.g("JDevice", "collect failed");
        }
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JDevice";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        JSONObject jSONObject = this.b;
        if (jSONObject == null) {
            cn.jiguang.ai.a.c("JDevice", "there are no data to report");
            return;
        }
        i.a(context, jSONObject, DeviceRequestsHelper.DEVICE_INFO_PARAM);
        i.a(context, this.b, new b(this, context, str));
        this.b = null;
    }

    @Override // cn.jiguang.f.a
    public final Object f(Context context) {
        return g(context);
    }
}
