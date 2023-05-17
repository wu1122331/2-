package cn.jiguang.h;

import android.content.Context;
import cn.jiguang.f.i;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d extends cn.jiguang.f.a {
    private static volatile d c;
    private Context a;
    private String[] b;

    public static d d() {
        if (c == null) {
            synchronized (d.class) {
                c = new d();
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean a() {
        cn.jiguang.ai.a.c("JAppPermission", "for googlePlay:false");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            this.b = strArr;
            if (strArr == null || strArr.length <= 0) {
                cn.jiguang.ai.a.c("JAppPermission", "collect failed");
            } else {
                cn.jiguang.ai.a.c("JAppPermission", "collect success:" + Arrays.toString(this.b));
            }
        } catch (Throwable th) {
            this.b = null;
            cn.jiguang.ai.a.g("JAppPermission", "collect throwable:" + th.getMessage());
        }
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JAppPermission";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        String[] strArr = this.b;
        if (strArr == null || strArr.length == 0) {
            cn.jiguang.ai.a.g("JAppPermission", "there are no data to report");
            return;
        }
        int length = strArr.length;
        StringBuilder sb = new StringBuilder("[");
        String i = cn.jiguang.sdk.impl.b.i(context);
        long e = cn.jiguang.sdk.impl.b.e(context);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            (i3 == 0 ? sb.append("\"") : sb.append(",\"")).append(this.b[i2]).append("\"");
            i2++;
            i3++;
            if (i3 >= 50 || sb.length() > 1000 || i2 == length) {
                sb.append("]");
                String format = String.format(Locale.ENGLISH, "{\"total\":%d,\"page\":%d,\"senderid\":\"%s\",\"uid\":%s,\"permission_list\":%s}", Integer.valueOf(length), Integer.valueOf(i4), i, Long.valueOf(e), sb.toString());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("data", format);
                } catch (JSONException e2) {
                    cn.jiguang.ai.a.g("JAppPermission", "package json exception:" + e2.getMessage());
                }
                i.a(context, jSONObject, "android_permissions");
                i.a(context, (Object) jSONObject);
                super.d(context, str);
                i4++;
                sb = new StringBuilder("[");
                i3 = 0;
            }
        }
        this.b = null;
    }
}
