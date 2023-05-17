package cn.jiguang.as;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class b {
    private static final Object b = new Object();
    private static ConcurrentHashMap<String, ThreadLocal<SimpleDateFormat>> c = new ConcurrentHashMap<>();
    public static String a = "yyyyMMdd_HHmm";

    public static String a() {
        return a("yyyy-MM-dd_HH:mm:ss").format(new Date());
    }

    public static SimpleDateFormat a(String str) {
        ThreadLocal<SimpleDateFormat> threadLocal = c.get(str);
        if (threadLocal == null) {
            synchronized (b) {
                threadLocal = c.get(str);
                if (threadLocal == null) {
                    threadLocal = new c(str);
                    c.put(str, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }

    public static boolean a(Date date, int i) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date.getTime());
        calendar.roll(6, -2);
        return calendar.after(calendar2);
    }

    public static String b() {
        return a(a).format(new Date());
    }

    public static Date b(String str) {
        try {
            return a(a).parse(str);
        } catch (ParseException e) {
            cn.jiguang.ac.d.a("DateUtil", "parse filename datetime error - " + str, e);
            return null;
        }
    }
}
