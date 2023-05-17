package cn.jiguang.as;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public final class g {
    public static boolean a(Context context, Class<?> cls) {
        boolean z;
        boolean z2 = false;
        try {
            z = !context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty();
        } catch (Throwable unused) {
        }
        if (z) {
            return z;
        }
        try {
            if (cn.jiguang.ap.a.c(context, context.getPackageName(), cls) != null) {
                z2 = true;
            }
        } catch (Throwable unused2) {
            z2 = z;
        }
        return z2;
    }
}
