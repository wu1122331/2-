package cn.jiguang.ac;

import android.os.Environment;
import cn.jiguang.as.j;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public final class b {
    private static String a = ".jpush";
    private static String b = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + a + File.separator;
    private static String c = b + a;
    private static final SimpleDateFormat d = new SimpleDateFormat("MM.dd_HH:mm:ss_SSS", Locale.ENGLISH);
    private static ArrayList<String> e = new ArrayList<>();
    private static boolean f = false;
    private static boolean g = false;

    public static void a(String str, String str2, String str3, Throwable th) {
        str2 = (str2 == null || str2.trim().equals("")) ? "Logger" : "Logger";
        if (str3 == null) {
            return;
        }
        try {
            String format = d.format(new Date());
            BufferedReader bufferedReader = new BufferedReader(new StringReader(str3), 256);
            String a2 = j.a("[" + str2 + "]", 24);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return;
                    }
                    String str4 = ((Object) format) + " " + j.a(str, 5) + " " + a2 + " " + readLine;
                    if (!g) {
                        try {
                            e.add(str4);
                            if (e.size() == 500) {
                                ArrayList<String> arrayList = e;
                                e = new ArrayList<>();
                                boolean a3 = cn.jiguang.ap.a.a(cn.jiguang.a.a.a, "android.permission.WRITE_EXTERNAL_STORAGE");
                                f = a3;
                                if (a3) {
                                    d.a("Logger", "have writable external storage, write log file");
                                    a(arrayList);
                                } else {
                                    d.a("Logger", "no writable external storage");
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e2) {
                            e2.printStackTrace();
                            e = new ArrayList<>();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    d.h("Logger", e3.getMessage());
                    return;
                }
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
    }

    private static void a(ArrayList<String> arrayList) {
        try {
            if (cn.jiguang.a.a.a == null || cn.jiguang.ap.a.a(cn.jiguang.a.a.a, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                cn.jiguang.ar.a.a("ASYNC", new c(arrayList));
            } else {
                d.f("Logger", "WRITE_EXTERNAL_STORAGE not get");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c() {
        File[] listFiles;
        try {
            File file = new File(b);
            if (file.exists()) {
                int length = a.length() + 1;
                int length2 = cn.jiguang.as.b.a.length() + length;
                if (file.listFiles() != null) {
                    for (File file2 : file.listFiles()) {
                        if (cn.jiguang.as.b.a(cn.jiguang.as.b.b(file2.getName().substring(length, length2)), 2)) {
                            file2.delete();
                        }
                    }
                }
            }
        } catch (Throwable th) {
            d.h("Logger", th.getMessage());
        }
    }
}
