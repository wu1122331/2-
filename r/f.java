package cn.jiguang.r;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class f {
    private static int a;

    private static int a(int i) {
        try {
            Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("getSubId", Integer.TYPE);
            declaredMethod.setAccessible(true);
            int[] iArr = (int[]) declaredMethod.invoke(null, Integer.valueOf(i));
            return iArr.length > 0 ? iArr[0] : i;
        } catch (Throwable unused) {
            return i;
        }
    }

    private static int a(TelephonyManager telephonyManager) {
        try {
            return ((Integer) telephonyManager.getClass().getMethod("getSimCount", new Class[0]).invoke(telephonyManager, new Object[0])).intValue();
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static ArrayList<cn.jiguang.q.b> a(Context context) {
        if (cn.jiguang.sdk.impl.b.a(context, false, "do not get sim info")) {
            return null;
        }
        int i = a;
        if ((i == 0 || i == 1) && a()) {
            a = 1;
            return b(context);
        }
        int i2 = a;
        if ((i2 == 0 || i2 == 2) && c(context)) {
            a = 2;
            return d(context);
        }
        int i3 = a;
        if ((i3 == 0 || i3 == 3) && e(context)) {
            a = 3;
            return f(context);
        }
        int i4 = a;
        if ((i4 == 0 || i4 == 4) && g(context)) {
            a = 4;
            return h(context);
        }
        a = 1;
        return b(context);
    }

    private static boolean a() {
        try {
            return TelephonyManager.class.getMethod("getSimCount", new Class[0]) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static ArrayList<cn.jiguang.q.b> b(Context context) {
        ArrayList<cn.jiguang.q.b> arrayList = new ArrayList<>();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int a2 = a(telephonyManager);
            if (a2 > 0) {
                Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getImei", Integer.TYPE);
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = telephonyManager.getClass().getDeclaredMethod("getSimSerialNumber", Integer.TYPE);
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", Integer.TYPE);
                declaredMethod3.setAccessible(true);
                for (int i = 0; i < a2; i++) {
                    int a3 = a(i);
                    cn.jiguang.q.b bVar = new cn.jiguang.q.b();
                    try {
                        bVar.a = c.a((String) declaredMethod.invoke(telephonyManager, Integer.valueOf(i)));
                        bVar.c = (String) declaredMethod2.invoke(telephonyManager, Integer.valueOf(a3));
                        bVar.b = (String) declaredMethod3.invoke(telephonyManager, Integer.valueOf(a3));
                    } catch (Throwable unused) {
                    }
                    arrayList.add(bVar);
                }
            }
        } catch (Throwable unused2) {
            arrayList.clear();
        }
        return arrayList;
    }

    private static ArrayList<Integer> b(TelephonyManager telephonyManager) {
        Field[] declaredFields;
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            int i = 0;
            for (Field field : TelephonyManager.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (TextUtils.equals(field.getType().getName(), "com.android.internal.telephony.ITelephonyRegistry") && field.get(telephonyManager) != null) {
                    arrayList.add(Integer.valueOf(i));
                    i++;
                }
            }
        } catch (Throwable unused) {
            arrayList.clear();
            arrayList.add(0);
            arrayList.add(1);
        }
        return arrayList;
    }

    private static boolean c(Context context) {
        try {
            b((TelephonyManager) context.getSystemService("phone"));
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", Integer.TYPE);
            Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", Integer.TYPE);
            Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", Integer.TYPE);
            Field declaredField = TelephonyManager.class.getDeclaredField("mtkGeminiSupport");
            if (declaredMethod != null && declaredMethod2 != null && declaredMethod3 != null && declaredField != null) {
                declaredField.setAccessible(true);
                if (((Boolean) declaredField.get(null)).booleanValue()) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private static ArrayList<cn.jiguang.q.b> d(Context context) {
        ArrayList<cn.jiguang.q.b> arrayList = new ArrayList<>();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", Integer.TYPE);
            Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", Integer.TYPE);
            Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getSimSerialNumberGemini", Integer.TYPE);
            ArrayList<Integer> b = b(telephonyManager);
            for (int i = 0; i < b.size(); i++) {
                cn.jiguang.q.b bVar = new cn.jiguang.q.b();
                try {
                    int intValue = b.get(i).intValue();
                    bVar.b = (String) declaredMethod.invoke(telephonyManager, Integer.valueOf(intValue));
                    bVar.a = c.a((String) declaredMethod2.invoke(telephonyManager, Integer.valueOf(intValue)));
                    bVar.c = (String) declaredMethod3.invoke(telephonyManager, Integer.valueOf(intValue));
                } catch (Throwable unused) {
                }
                arrayList.add(bVar);
            }
            return arrayList;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static boolean e(Context context) {
        Class<?> cls;
        try {
            cls = Class.forName("android.telephony.MSimTelephonyManager");
        } catch (Throwable unused) {
        }
        return (context.getSystemService("phone_msim") == null || cls.getMethod("getDeviceId", Integer.TYPE) == null || cls.getMethod("getSubscriberId", Integer.TYPE) == null) ? false : true;
    }

    private static ArrayList<cn.jiguang.q.b> f(Context context) {
        ArrayList<cn.jiguang.q.b> arrayList = new ArrayList<>();
        try {
            Class<?> cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = context.getSystemService("phone_msim");
            Method method = cls.getMethod("getDeviceId", Integer.TYPE);
            Method method2 = cls.getMethod("getSubscriberId", Integer.TYPE);
            cn.jiguang.q.b bVar = new cn.jiguang.q.b();
            try {
                bVar.a = c.a((String) method.invoke(systemService, 0));
                bVar.b = (String) method2.invoke(systemService, 0);
            } catch (Throwable unused) {
            }
            arrayList.add(bVar);
            cn.jiguang.q.b bVar2 = new cn.jiguang.q.b();
            try {
                bVar2.a = c.a((String) method.invoke(systemService, 1));
                bVar2.b = (String) method2.invoke(systemService, 1);
            } catch (Throwable unused2) {
            }
            arrayList.add(bVar2);
            return arrayList;
        } catch (Throwable unused3) {
            return null;
        }
    }

    private static boolean g(Context context) {
        Method method;
        String str;
        try {
            Class<?> cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            method = cls.getMethod("getServiceName", String.class, Integer.TYPE);
            str = (String) method.invoke(cls, "phone", 1);
        } catch (Throwable unused) {
        }
        return (method == null || str == null || ((TelephonyManager) context.getSystemService(str)) == null) ? false : true;
    }

    private static ArrayList<cn.jiguang.q.b> h(Context context) {
        ArrayList<cn.jiguang.q.b> arrayList = new ArrayList<>();
        try {
            Class<?> cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService((String) cls.getMethod("getServiceName", String.class, Integer.TYPE).invoke(cls, "phone", 1));
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone");
            cn.jiguang.q.b bVar = new cn.jiguang.q.b();
            try {
                bVar.b = telephonyManager2.getSubscriberId();
                bVar.a = telephonyManager2.getDeviceId();
                bVar.c = telephonyManager2.getSimSerialNumber();
            } catch (Throwable unused) {
            }
            arrayList.add(bVar);
            cn.jiguang.q.b bVar2 = new cn.jiguang.q.b();
            try {
                bVar2.b = telephonyManager.getSubscriberId();
                bVar2.a = telephonyManager.getDeviceId();
                bVar2.c = telephonyManager.getSimSerialNumber();
            } catch (Throwable unused2) {
            }
            arrayList.add(bVar2);
            return arrayList;
        } catch (Throwable unused3) {
            return null;
        }
    }
}
