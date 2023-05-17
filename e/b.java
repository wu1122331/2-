package cn.jiguang.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class b {
    private static final Map<String, SharedPreferences> a = new ConcurrentHashMap();

    public static <T> T a(Context context, a<T> aVar) {
        T t = (T) b(context, aVar);
        return t != null ? t : aVar.c;
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

    private static <T> void a(Context context, a<T> aVar, a<T> aVar2) {
        T t;
        if (b(context, aVar) != null || (t = (T) b(context, aVar2)) == null) {
            return;
        }
        aVar.c = t;
        a(context, aVar);
        aVar2.c = null;
        a(context, aVar2);
    }

    public static void a(Context context, String str) {
        SharedPreferences b = b(context, str);
        if (b != null) {
            b.edit().clear().apply();
        }
    }

    public static void a(Context context, a<?>... aVarArr) {
        if (aVarArr.length > 0) {
            SharedPreferences b = b(context, aVarArr[0].a);
            if (b != null) {
                SharedPreferences.Editor edit = b.edit();
                for (a<?> aVar : aVarArr) {
                    String str = aVar.b;
                    T t = aVar.c;
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

    private static SharedPreferences b(Context context, String str) {
        Context a2;
        a e;
        a e2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map<String, SharedPreferences> map = a;
        SharedPreferences sharedPreferences = map.get(str);
        if (sharedPreferences == null && (a2 = cn.jiguang.a.a.a(context)) != null) {
            sharedPreferences = a2.getSharedPreferences(str, 0);
            map.put(str, sharedPreferences);
            String str2 = (String) a(a2, a.v());
            if (TextUtils.isEmpty(str2) || str2.startsWith("1.")) {
                if (str.equals(a.e)) {
                    a<String> i = a.i();
                    a<String> i2 = a.i();
                    i2.a = "cn.jpush.android.user.profile";
                    a(a2, i, i2);
                    a<Long> j = a.j();
                    a<Long> j2 = a.j();
                    j2.a = "cn.jpush.android.user.profile";
                    a(a2, j, j2);
                    e = a.k();
                    e2 = a.k();
                    e2.a = "cn.jpush.android.user.profile";
                } else if (str.equals("cn.jiguang.sdk.user.set.profile")) {
                    a<String> l = a.l();
                    a<String> l2 = a.l();
                    l2.a = "cn.jpush.preferences.v2";
                    a(a2, l, l2);
                    e = a.m();
                    e2 = a.m();
                    e2.c = "cn.jpush.android.user.profile";
                } else if (str.equals("cn.jiguang.sdk.user.profile")) {
                    a<Long> c = a.c();
                    a<Long> c2 = a.c();
                    c2.a = "cn.jpush.android.user.profile";
                    c2.b = "device_uid";
                    a(a2, c, c2);
                    a<String> d = a.d();
                    a<String> d2 = a.d();
                    d2.a = "cn.jpush.android.user.profile";
                    d2.b = "device_registration_id";
                    a(a2, d, d2);
                    e = a.e();
                    e2 = a.e();
                    e2.a = "cn.jpush.android.user.profile";
                    e2.b = "device_password";
                }
                a(a2, e, e2);
            }
        }
        return sharedPreferences;
    }

    public static <T> T b(Context context, a<T> aVar) {
        SharedPreferences sharedPreferences;
        T t = (T) a(b(context, aVar.a), aVar.b, aVar.c);
        if (t == null && aVar.d) {
            String str = aVar.a;
            Context a2 = cn.jiguang.a.a.a(context);
            if (a2 != null) {
                if (Build.VERSION.SDK_INT >= 11) {
                    a2.getSharedPreferences(str, 4);
                }
                sharedPreferences = a2.getSharedPreferences(str, 0);
            } else {
                sharedPreferences = null;
            }
            t = (T) a(sharedPreferences, aVar.b, aVar.c);
        }
        if (t != null) {
            aVar.c = t;
            return t;
        }
        return null;
    }
}
