package cn.jiguang.ac;

import android.util.Log;

/* loaded from: classes.dex */
public final class a {
    public static void a(String str, String str2, boolean z, int i, String str3) {
        String str4;
        if (cn.jiguang.a.a.c || i >= 5) {
            if (!z || i >= 6) {
                if (i >= 2) {
                    if (i == 2) {
                        Log.v("JIGUANG-" + str, "[" + str2 + "] " + str3);
                        str4 = "TRACE";
                    } else if (i == 3) {
                        Log.d("JIGUANG-" + str, "[" + str2 + "] " + str3);
                        str4 = "DEBUG";
                    } else if (i == 4) {
                        Log.i("JIGUANG-" + str, "[" + str2 + "] " + str3);
                        str4 = "INFO";
                    } else if (i != 5) {
                        if (i != 6) {
                            return;
                        }
                        Log.e("JIGUANG-" + str, "[" + str2 + "] " + str3);
                        b.a("ERROR", str2, str3, null);
                        return;
                    } else {
                        Log.w("JIGUANG-" + str, "[" + str2 + "] " + str3);
                        str4 = "WARN";
                    }
                    b.a(str4, str2, str3, null);
                }
            }
        }
    }
}
