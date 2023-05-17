package cn.jiguang.e;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.ac.d;

/* loaded from: classes.dex */
public final class c {
    private static volatile Long a;
    private static volatile Long b;

    public static long a(Context context, long j) {
        return (j + c(context)) / 1000;
    }

    public static boolean a(Context context) {
        String str;
        if (((Long) b.a(context, a.c())).longValue() <= 0) {
            str = "isValidRegistered uid <= 0";
        } else if (!TextUtils.isEmpty((String) b.a(context, a.d()))) {
            return true;
        } else {
            str = "isValidRegistered regId is empty";
        }
        d.a("SpHelper", str);
        return false;
    }

    public static long b(Context context) {
        return a(context, System.currentTimeMillis());
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Long] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Long] */
    public static void b(Context context, long j) {
        if (j > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            a = Long.valueOf(j);
            b = Long.valueOf(currentTimeMillis);
            a<Long> h = a.h();
            h.c = Long.valueOf(j);
            a<Long> g = a.g();
            g.c = Long.valueOf(currentTimeMillis);
            b.a(context, h, g);
        }
    }

    public static long c(Context context) {
        if (a == null || b == null) {
            long longValue = ((Long) b.a(context, a.g())).longValue();
            long longValue2 = ((Long) b.a(context, a.h())).longValue();
            if (longValue == 0 || longValue2 == 0) {
                return 0L;
            }
            a = Long.valueOf(longValue2);
            b = Long.valueOf(longValue);
            return longValue2 - longValue;
        }
        return a.longValue() - b.longValue();
    }
}
