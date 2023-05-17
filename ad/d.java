package cn.jiguang.ad;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class d {
    private static String a;
    private static String b;
    private static ConcurrentHashMap<File, Boolean> c = new ConcurrentHashMap<>();
    private static final AtomicBoolean d = new AtomicBoolean(false);

    public static File a(Context context, String str, JSONObject jSONObject, boolean z) {
        File file = new File(e(context) + str + File.separator + UUID.randomUUID().toString());
        if (z) {
            b(file);
        }
        cn.jiguang.as.e.a(file, jSONObject.toString());
        return file;
    }

    public static void a(Context context) {
        cn.jiguang.as.f fVar = new cn.jiguang.as.f(false, true, "jpush_stat_history", 1);
        String b2 = cn.jiguang.ab.f.b(context);
        File[] a2 = cn.jiguang.as.e.a(context.getFilesDir(), fVar, new e());
        if (a2 != null) {
            for (File file : a2) {
                try {
                    cn.jiguang.as.e.c(new File(file, "nowrap"));
                    File file2 = new File(file.getParent() + File.separator + b2 + File.separator + file.getName());
                    if (!file2.getParentFile().exists()) {
                        file2.getParentFile().mkdirs();
                    }
                    file.renameTo(file2);
                } catch (Throwable th) {
                    cn.jiguang.ac.d.b("updateByAppKey", "e=" + th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(Context context, File file) {
        cn.jiguang.ac.d.b("ReportHistory", "upload space=" + e(file));
        c(context, file);
        d(file);
        b(context, file);
        c(file);
    }

    public static void a(Context context, String... strArr) {
        FileFilter[] fileFilterArr = new FileFilter[2];
        for (int i = 0; i <= 0; i++) {
            fileFilterArr[1] = cn.jiguang.as.f.a(strArr[0]);
        }
        fileFilterArr[0] = new cn.jiguang.as.f(false, true, "jpush_stat_history", 1);
        File[] a2 = cn.jiguang.as.e.a(context.getFilesDir(), fileFilterArr);
        if (a2 != null) {
            for (File file : a2) {
                cn.jiguang.as.e.c(file);
            }
        }
    }

    public static void a(File file) {
        if (file != null) {
            c.remove(file);
        }
    }

    private static Set<String> b() {
        HashSet hashSet = new HashSet();
        hashSet.add("uid");
        hashSet.add("app_key");
        hashSet.add("sdk_ver");
        hashSet.add("channel");
        hashSet.add("app_version");
        JSONObject jSONObject = new JSONObject();
        cn.jiguang.ab.a.a();
        cn.jiguang.ab.a.a(jSONObject);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            hashSet.add(keys.next());
        }
        return hashSet;
    }

    public static void b(Context context) {
        if (d.get()) {
            cn.jiguang.ac.d.b("ReportHistory", "isRunning, no need report");
        } else {
            cn.jiguang.ar.a.a("REPORT_HISTORY", new f(context));
        }
    }

    private static void b(Context context, File file) {
        if (((Long) cn.jiguang.e.b.a(context, cn.jiguang.e.a.c())).longValue() == 0) {
            cn.jiguang.ac.d.b("ReportHistory", "can't get uid, skip upload history");
            return;
        }
        File[] a2 = cn.jiguang.as.e.a(file, cn.jiguang.as.f.a);
        if (a2 == null || a2.length == 0) {
            return;
        }
        LinkedList<File> linkedList = new LinkedList();
        for (File file2 : a2) {
            if (!Boolean.TRUE.equals(c.get(file2))) {
                linkedList.add(file2);
            }
        }
        if (linkedList.isEmpty()) {
            return;
        }
        cn.jiguang.ac.d.b("ReportHistory", "process space=" + e(file) + " history[" + linkedList.size() + "]");
        Collections.sort(linkedList, new g());
        for (File file3 : linkedList) {
            try {
                JSONObject a3 = c.a(file3);
                if (a3 == null) {
                    cn.jiguang.as.e.a(file3);
                } else {
                    b(file3);
                    Set<String> a4 = k.a(a3);
                    cn.jiguang.ac.d.b("ReportHistory", "report history types=" + a4);
                    if (k.a(context, a4, a3, file3, null) == -2) {
                        return;
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.ac.d.f("ReportHistory", "upload e:" + th);
            }
        }
    }

    private static void b(File file) {
        if (file != null) {
            c.put(file, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065 A[Catch: all -> 0x0081, TryCatch #0 {all -> 0x0081, blocks: (B:3:0x0002, B:5:0x000a, B:12:0x0017, B:14:0x002a, B:17:0x002e, B:18:0x0062, B:20:0x0065, B:24:0x0070, B:27:0x0077, B:29:0x007c), top: B:34:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007c A[Catch: all -> 0x0081, TRY_LEAVE, TryCatch #0 {all -> 0x0081, blocks: (B:3:0x0002, B:5:0x000a, B:12:0x0017, B:14:0x002a, B:17:0x002e, B:18:0x0062, B:20:0x0065, B:24:0x0070, B:27:0x0077, B:29:0x007c), top: B:34:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void c(android.content.Context r6, java.io.File r7) {
        /*
            java.lang.String r0 = "ReportHistory"
            org.json.JSONObject r6 = cn.jiguang.ad.k.a(r6)     // Catch: java.lang.Throwable -> L81
            r1 = 0
            r2 = 1
            if (r6 == 0) goto L13
            int r3 = r6.length()     // Catch: java.lang.Throwable -> L81
            if (r3 != 0) goto L11
            goto L13
        L11:
            r3 = 0
            goto L14
        L13:
            r3 = 1
        L14:
            if (r3 == 0) goto L17
            return
        L17:
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L81
            java.lang.String r4 = "nowrap"
            r3.<init>(r7, r4)     // Catch: java.lang.Throwable -> L81
            java.io.FileFilter[] r4 = new java.io.FileFilter[r2]     // Catch: java.lang.Throwable -> L81
            cn.jiguang.as.f r5 = cn.jiguang.as.f.a     // Catch: java.lang.Throwable -> L81
            r4[r1] = r5     // Catch: java.lang.Throwable -> L81
            java.io.File[] r3 = cn.jiguang.as.e.a(r3, r4)     // Catch: java.lang.Throwable -> L81
            if (r3 == 0) goto L80
            int r4 = r3.length     // Catch: java.lang.Throwable -> L81
            if (r4 != 0) goto L2e
            goto L80
        L2e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = "process space="
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = e(r7)     // Catch: java.lang.Throwable -> L81
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = " nowrap["
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L81
            int r5 = r3.length     // Catch: java.lang.Throwable -> L81
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = "]"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L81
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L81
            cn.jiguang.ac.d.b(r0, r4)     // Catch: java.lang.Throwable -> L81
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L81
            java.lang.String r5 = "tmp"
            r4.<init>(r7, r5)     // Catch: java.lang.Throwable -> L81
            r7 = r3[r1]     // Catch: java.lang.Throwable -> L81
            cn.jiguang.ad.c r7 = cn.jiguang.ad.c.a(r7, r6)     // Catch: java.lang.Throwable -> L81
        L62:
            int r1 = r3.length     // Catch: java.lang.Throwable -> L81
            if (r2 >= r1) goto L7a
            r1 = r3[r2]     // Catch: java.lang.Throwable -> L81
            cn.jiguang.ad.c r1 = cn.jiguang.ad.c.a(r1, r6)     // Catch: java.lang.Throwable -> L81
            if (r1 == 0) goto L77
            if (r7 != 0) goto L70
            goto L76
        L70:
            boolean r5 = r7.a(r1, r4)     // Catch: java.lang.Throwable -> L81
            if (r5 != 0) goto L77
        L76:
            r7 = r1
        L77:
            int r2 = r2 + 1
            goto L62
        L7a:
            if (r7 == 0) goto L80
            r6 = 0
            r7.a(r6, r4)     // Catch: java.lang.Throwable -> L81
        L80:
            return
        L81:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "processNowrap e:"
            r7.<init>(r1)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r6 = r6.toString()
            cn.jiguang.ac.d.f(r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ad.d.c(android.content.Context, java.io.File):void");
    }

    private static void c(File file) {
        int i = 0;
        File[] a2 = cn.jiguang.as.e.a(file, cn.jiguang.as.f.a);
        if (a2 == null || a2.length <= 1) {
            return;
        }
        List<File> asList = Arrays.asList(a2);
        Collections.sort(asList, new h());
        for (File file2 : asList) {
            i = (int) (i + file2.length());
            if (i > 1048576) {
                cn.jiguang.as.e.a(file2);
            }
        }
        if (asList.size() < a2.length) {
            cn.jiguang.ac.d.b("ReportHistory", "trim space history=" + e(file) + "," + a2.length + " to " + asList.size());
        }
    }

    private static synchronized String d(Context context) {
        synchronized (d.class) {
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            if (a == null) {
                String a2 = cn.jiguang.as.a.a(context);
                if (!TextUtils.isEmpty(a2)) {
                    a = a2.equals(context.getPackageName()) ? "" : a2.replaceFirst(context.getPackageName() + CertificateUtil.DELIMITER, "_");
                }
            }
            File a3 = cn.jiguang.as.e.a(context, a != null ? "jpush_stat_history" + a : "jpush_stat_history");
            if (a3 == null) {
                return "jpush_stat_history" + a;
            }
            String absolutePath = a3.getAbsolutePath();
            b = absolutePath;
            return absolutePath;
        }
    }

    private static void d(File file) {
        try {
            File[] a2 = cn.jiguang.as.e.a(new File(file, "tmp"), cn.jiguang.as.f.a);
            if (a2 != null && a2.length != 0) {
                LinkedList<File> linkedList = new LinkedList();
                for (File file2 : a2) {
                    if (!Boolean.TRUE.equals(c.get(file2))) {
                        linkedList.add(file2);
                    }
                }
                if (linkedList.isEmpty()) {
                    return;
                }
                cn.jiguang.ac.d.b("ReportHistory", "process space=" + e(file) + " tmp[" + linkedList.size() + "]");
                Set<String> b2 = b();
                HashMap hashMap = new HashMap();
                c a3 = c.a(cn.jiguang.as.e.a(cn.jiguang.as.e.a(file, cn.jiguang.as.f.a)), b2);
                if (a3 != null) {
                    hashMap.put(String.valueOf(a3.b), a3);
                }
                for (File file3 : linkedList) {
                    c a4 = c.a(file3, b2);
                    if (a4 != null) {
                        String valueOf = String.valueOf(a4.b);
                        c cVar = (c) hashMap.get(valueOf);
                        if (cVar != null && cVar.a(a4, file)) {
                        }
                        hashMap.put(valueOf, a4);
                    }
                }
                for (c cVar2 : hashMap.values()) {
                    cVar2.a((c) null, file);
                }
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("ReportHistory", "processTmp e:" + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(Context context) {
        String b2 = cn.jiguang.ab.f.b(context);
        if (TextUtils.isEmpty(b2)) {
            b2 = "0";
        }
        return d(context) + File.separator + b2;
    }

    private static String e(File file) {
        try {
            File parentFile = file.getParentFile();
            return parentFile.getParentFile().getName() + File.separator + parentFile.getName() + File.separator + file.getName();
        } catch (Throwable unused) {
            return file.getAbsolutePath();
        }
    }
}
