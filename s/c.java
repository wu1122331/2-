package cn.jiguang.s;

import android.text.TextUtils;
import cn.jiguang.ap.h;

/* loaded from: classes.dex */
public final class c {
    private static long a = 1;

    public static long a() {
        long j = a + 1;
        a = j;
        if (j >= 2147483647L) {
            a = 1L;
        }
        return a;
    }

    public static byte[] a(long j, String str) {
        cn.jiguang.t.a aVar = new cn.jiguang.t.a(20480);
        aVar.a(0);
        aVar.a(j);
        if (str != null && !TextUtils.isEmpty(str)) {
            byte[] j2 = h.j(str);
            aVar.a(j2.length);
            aVar.a(j2, 0, j2.length);
        }
        return aVar.a();
    }
}
