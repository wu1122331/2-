package cn.jiguang.aj;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.ap.h;
import cn.jiguang.ap.i;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class c {
    private static int a;
    private static c b;
    private final Map<String, Integer> c = new HashMap();

    private c() {
    }

    private static int a(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            boolean z2 = false;
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (!h.a(nextElement.getName(), "dummy")) {
                    boolean a2 = h.a(nextElement.getName(), "wlan");
                    if (!z || a2) {
                        Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement2 = inetAddresses.nextElement();
                            if (!nextElement2.isLoopbackAddress() && a(nextElement2)) {
                                if (a2) {
                                    return 3;
                                }
                                z2 = true;
                            }
                        }
                        continue;
                    }
                }
            }
            return z2 ? 0 : 1;
        } catch (Exception unused) {
            cn.jiguang.ai.a.g("IpvxHelper", "checkIpvxSupport:");
            return 0;
        }
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public static void a(int i) {
        if (i > 3 || i < 0) {
            return;
        }
        a = i;
    }

    private static boolean a(InetAddress inetAddress) {
        try {
            if (inetAddress instanceof Inet6Address) {
                if (!inetAddress.getHostAddress().substring(0, 4).equalsIgnoreCase("fe80")) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static int b(int i) {
        int i2 = a;
        if (i2 == 2 || i2 == 1) {
            return i2;
        }
        if (i != 1) {
            if (i != 2) {
                return i2;
            }
            return 2;
        }
        return 1;
    }

    public final int a(Context context) {
        String c;
        String str;
        boolean equals;
        boolean z;
        Integer num;
        int i;
        int i2 = 0;
        try {
            c = i.c(context);
            str = "";
            equals = "wifi".equals(c);
            if (equals && cn.jiguang.sdk.impl.b.r(context)) {
                str = cn.jiguang.ap.a.j(context);
            }
            z = !TextUtils.isEmpty(str);
        } catch (Throwable th) {
            th = th;
        }
        if ((!equals || z) && (num = this.c.get(c + str)) != null && num.intValue() != 0) {
            cn.jiguang.ai.a.c("IpvxHelper", "net=" + c + " " + str + " get cache support=" + num);
            return num.intValue();
        }
        if (z) {
            i = ((Integer) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.g(str))).intValue();
            try {
                cn.jiguang.ai.a.c("IpvxHelper", "net=" + c + " " + str + " get wifi history support=" + i);
            } catch (Throwable th2) {
                th = th2;
                i2 = i;
                cn.jiguang.ai.a.g("IpvxHelper", "getPreferVx e:" + th);
                return i2;
            }
        } else {
            i = 0;
        }
        if (i == 0) {
            i = a(equals);
            cn.jiguang.ai.a.c("IpvxHelper", "net=" + c + " " + str + " get networkinterface support=" + i);
            if (z) {
                cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.g(str).a((cn.jiguang.ae.b<Integer>) Integer.valueOf(i)));
            }
        }
        i2 = i;
        if (!equals || z) {
            this.c.put(c + str, Integer.valueOf(i2));
        }
        return i2;
    }

    public final void a(Context context, int i) {
        String c = i.c(context);
        boolean equals = "wifi".equals(c);
        String j = (equals && cn.jiguang.sdk.impl.b.r(context)) ? cn.jiguang.ap.a.j(context) : "";
        boolean z = !TextUtils.isEmpty(j);
        if (!equals || z) {
            this.c.put(c + j, Integer.valueOf(i));
        }
        if (z) {
            cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.g(j).a((cn.jiguang.ae.b<Integer>) Integer.valueOf(i)));
        }
    }
}
