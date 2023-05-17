package cn.jiguang.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import cn.jiguang.a.a;

/* loaded from: classes.dex */
public class MultiSpHelper {
    private static final String SP_FILE = "cn.jpush.android.user.profile";
    private static final String TAG = "MultiSpHelper";
    private static SharedPreferences sharedPref;

    private MultiSpHelper() {
    }

    public static void commitBoolean(Context context, String str, boolean z) {
        getSp(context).edit().putBoolean(str, z).apply();
    }

    public static void commitInt(Context context, String str, int i) {
        getSp(context).edit().putInt(str, i).apply();
    }

    public static void commitLong(Context context, String str, long j) {
        getSp(context).edit().putLong(str, j).apply();
    }

    public static void commitString(Context context, String str, String str2) {
        getSp(context).edit().putString(str, str2).apply();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getSp(context).getBoolean(str, z);
    }

    public static int getInt(Context context, String str, int i) {
        SharedPreferences reload;
        return ((str.equals("jpush_register_code") || str.equals("service_stoped")) && (reload = reload(context)) != null) ? reload.getInt(str, i) : getSp(context).getInt(str, i);
    }

    public static long getLong(Context context, String str, long j) {
        return getSp(context).getLong(str, j);
    }

    private static SharedPreferences getSp(Context context) {
        if (sharedPref == null) {
            init(context);
        }
        return sharedPref;
    }

    public static String getString(Context context, String str, String str2) {
        return getSp(context).getString(str, str2);
    }

    private static void init(Context context) {
        sharedPref = context.getSharedPreferences(SP_FILE, 0);
    }

    private static SharedPreferences reload(Context context) {
        Context a = a.a(context);
        if (a != null) {
            if (Build.VERSION.SDK_INT >= 11) {
                a.getSharedPreferences(SP_FILE, 4);
            }
            return a.getSharedPreferences(SP_FILE, 0);
        }
        return null;
    }
}
