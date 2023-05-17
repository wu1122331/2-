package cn.jiguang.ae;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class c {
    private static final Map<String, SharedPreferences> a = new ConcurrentHashMap();

    private static SharedPreferences a(Context context, String str) {
        Context appContext;
        b<String> i;
        b<String> i2;
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map<String, SharedPreferences> map = a;
        SharedPreferences sharedPreferences = map.get(str);
        if (sharedPreferences == null && (appContext = JCoreManager.getAppContext(context)) != null) {
            sharedPreferences = appContext.getSharedPreferences(str, 0);
            map.put(str, sharedPreferences);
            String str3 = (String) a(appContext, b.g());
            if (TextUtils.isEmpty(str3) || str3.startsWith("1.")) {
                if (str.equals("cn.jiguang.sdk.address")) {
                    b<String> v = b.v();
                    b<String> v2 = b.v();
                    v2.a = "cn.jpush.android.user.profile";
                    v2.b = "conn";
                    a(appContext, v, v2);
                    i = b.w();
                    i2 = b.w();
                    i2.a = "cn.jpush.android.user.profile";
                    str2 = "srv";
                } else if (str.equals(b.e)) {
                    b<String> h = b.h();
                    b<String> h2 = b.h();
                    h2.b = "device_registered_appkey";
                    a(appContext, h, h2);
                    i = b.i();
                    i2 = b.i();
                    str2 = "imei";
                }
                i2.b = str2;
                a(appContext, i, i2);
            }
        }
        return sharedPreferences;
    }

    public static <T> T a(Context context, b<T> bVar) {
        T t = (T) b(context, bVar);
        return t != null ? t : bVar.c;
    }

    private static <T> T a(SharedPreferences sharedPreferences, String str, T t) {
        if (sharedPreferences != null && sharedPreferences.contains(str)) {
            try {
                if (t instanceof Boolean) {
                    return (T) Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue()));
                }
                if (t instanceof String) {
                    return (T) sharedPreferences.getString(str, (String) t);
                }
                if (t instanceof Integer) {
                    return (T) Integer.valueOf(sharedPreferences.getInt(str, ((Integer) t).intValue()));
                }
                if (t instanceof Long) {
                    return (T) Long.valueOf(sharedPreferences.getLong(str, ((Long) t).longValue()));
                }
                if (t instanceof Float) {
                    return (T) Float.valueOf(sharedPreferences.getFloat(str, ((Float) t).floatValue()));
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static <T> void a(Context context, b<T> bVar, b<T> bVar2) {
        T t;
        if (b(context, bVar) != null || (t = (T) b(context, bVar2)) == null) {
            return;
        }
        bVar.c = t;
        a(context, bVar);
        bVar2.c = null;
        a(context, bVar2);
    }

    public static void a(Context context, b<?>... bVarArr) {
        if (bVarArr.length > 0) {
            SharedPreferences a2 = a(context, bVarArr[0].a);
            if (a2 != null) {
                SharedPreferences.Editor edit = a2.edit();
                for (b<?> bVar : bVarArr) {
                    String str = bVar.b;
                    T t = bVar.c;
                    if (edit != null) {
                        if (t == 0) {
                            edit.remove(str);
                        } else if (t instanceof Boolean) {
                            edit.putBoolean(str, ((Boolean) t).booleanValue());
                        } else if (t instanceof String) {
                            edit.putString(str, (String) t);
                        } else if (t instanceof Integer) {
                            edit.putInt(str, ((Integer) t).intValue());
                        } else if (t instanceof Long) {
                            edit.putLong(str, ((Long) t).longValue());
                        } else if (t instanceof Float) {
                            edit.putFloat(str, ((Float) t).floatValue());
                        }
                    }
                }
                edit.commit();
            }
        }
    }

    public static <T> T b(Context context, b<T> bVar) {
        SharedPreferences sharedPreferences;
        T t = (T) a(a(context, bVar.a), bVar.b, bVar.c);
        if (t == null && bVar.d) {
            String str = bVar.a;
            Context appContext = JCoreManager.getAppContext(context);
            if (appContext != null) {
                if (Build.VERSION.SDK_INT >= 11) {
                    appContext.getSharedPreferences(str, 4);
                }
                sharedPreferences = appContext.getSharedPreferences(str, 0);
            } else {
                sharedPreferences = null;
            }
            t = (T) a(sharedPreferences, bVar.b, bVar.c);
        }
        if (t != null) {
            bVar.c = t;
            return t;
        }
        return null;
    }
}
