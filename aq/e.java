package cn.jiguang.aq;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Random;

/* loaded from: classes.dex */
public final class e {
    private static SharedPreferences a;

    private static long a(long j) {
        return (j + (j % 2 == 0 ? 1L : 2L)) % 32767;
    }

    public static synchronized long a(Context context) {
        long j;
        synchronized (e.class) {
            j = b(context).getLong("next_rid", -1L);
            if (j != -1) {
                j = a(j);
                b(context).edit().putLong("next_rid", j).apply();
            }
            if (j == -1) {
                j = a(Math.abs(new Random().nextInt(32767)));
                b(context).edit().putLong("next_rid", j).apply();
            }
        }
        return j;
    }

    private static SharedPreferences b(Context context) {
        if (a == null) {
            a = context.getSharedPreferences("cn.jpush.preferences.support.rid", 0);
        }
        return a;
    }
}
