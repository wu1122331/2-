package cn.jiguang.o;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class a {
    private static final Object a = new Object();
    private static ConcurrentHashMap<String, ThreadLocal<SimpleDateFormat>> b = new ConcurrentHashMap<>();

    public static String a() {
        return a("yyyyMMddHHmmss").format(new Date());
    }

    private static SimpleDateFormat a(String str) {
        ThreadLocal<SimpleDateFormat> threadLocal = b.get(str);
        if (threadLocal == null) {
            synchronized (a) {
                threadLocal = b.get(str);
                if (threadLocal == null) {
                    threadLocal = new b(str);
                    b.put(str, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }
}
