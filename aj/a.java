package cn.jiguang.aj;

import android.util.Pair;
import cn.jiguang.ap.h;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    private static volatile a a;
    private static final Object b = new Object();
    private long c = 36000000;
    private long d = 900000;
    private final Map<String, Pair<InetAddress[], Long>> e = new HashMap();

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private static InetAddress a(String str) {
        if (h.f(str) || h.g(str)) {
            try {
                return InetAddress.getByName(str);
            } catch (UnknownHostException e) {
                cn.jiguang.ai.a.g("DNSLoader", "dns resolve failed:" + e);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.net.InetAddress[] a(android.content.Context r20, java.lang.String r21, long r22, boolean r24) {
        /*
            Method dump skipped, instructions count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.aj.a.a(android.content.Context, java.lang.String, long, boolean):java.net.InetAddress[]");
    }
}
