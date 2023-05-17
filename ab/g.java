package cn.jiguang.ab;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jpush.android.service.DataShare;
import cn.jpush.android.service.JCommonService;
import cn.jpush.android.service.PushReceiver;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class g {
    private static g a;
    private static Map<String, String> b = new HashMap();

    private g() {
    }

    private static Bundle a(String str, String str2, Bundle bundle, String str3) {
        String str4 = str + "." + str2;
        try {
            cn.jiguang.d.a dataShare = DataShare.getInstance(str3);
            if (dataShare == null) {
                cn.jiguang.ac.d.b("JMessenger", str3 + "'s aidl not found, " + str4);
                return null;
            }
            cn.jiguang.ac.d.b("JMessenger", "sendByAidl, " + str4);
            if ("INTERNAL_API".equals(str)) {
                return dataShare.execute(str, str2, bundle);
            }
            dataShare.onAction(str, str2, bundle);
            return new Bundle();
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("JMessenger", "sendByAidl, " + str4 + ", e=" + th);
            return null;
        }
    }

    public static g a() {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g();
                }
            }
        }
        return a;
    }

    private static boolean a(Context context, Class cls, String str, Bundle bundle) {
        Intent intent;
        if (cls == null) {
            return false;
        }
        String str2 = cls.getName() + "." + str;
        try {
            intent = new Intent();
            intent.setClass(context, cls);
            intent.setAction(str);
            intent.setPackage(context.getPackageName());
            if (bundle != null) {
                intent.putExtras(bundle);
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("JMessenger", "sendByComponent, " + str2 + " e=" + th);
        }
        if (BroadcastReceiver.class.isAssignableFrom(cls)) {
            context.sendBroadcast(intent);
            cn.jiguang.ac.d.b("JMessenger", "sendByReceiver, " + str2);
            return true;
        }
        if (Service.class.isAssignableFrom(cls)) {
            if (context.startService(intent) != null) {
                cn.jiguang.ac.d.b("JMessenger", "sendByService, " + str2);
                return true;
            }
            cn.jiguang.ac.d.f("JMessenger", "startService, " + str2 + ", can't find component");
        }
        return false;
    }

    public static Bundle b(Context context, String str, String str2, Bundle bundle) {
        try {
            cn.jiguang.ac.d.b("JMessenger", "directHandle, " + str + "." + str2);
            if ("INTERNAL_API".equals(str)) {
                if (!str2.equals("isTcpLoggedIn")) {
                    cn.jiguang.ac.d.h("JMessenger", "directHandle, not support " + str + "." + str2);
                    return null;
                }
                boolean b2 = cn.jiguang.sdk.impl.b.b();
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean(ServerProtocol.DIALOG_PARAM_STATE, b2);
                return bundle2;
            }
            Class<?> cls = Class.forName(str);
            if (!BroadcastReceiver.class.isAssignableFrom(cls) && !Service.class.isAssignableFrom(cls)) {
                cn.jiguang.ac.d.h("JMessenger", "directHandle, not support " + str + "." + str2);
                return null;
            } else if (JCommonService.class.isAssignableFrom(cls)) {
                cn.jiguang.ac.d.b("JMessenger", "JCommonService handle succeed," + str + "." + str2);
                f.a(context, str2, bundle);
                return new Bundle();
            } else {
                Intent intent = new Intent(str2);
                intent.setClass(context, cls);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                intent.setPackage(context.getPackageName());
                intent.addCategory(context.getPackageName());
                if (PushReceiver.class.isAssignableFrom(cls)) {
                    cn.jiguang.ac.d.b("JMessenger", "PushReceiver handle succeed," + str + "." + str2);
                    String string = bundle != null ? bundle.getString("sdktype") : null;
                    a.a();
                    a.a(context, string, intent);
                } else if (BroadcastReceiver.class.isAssignableFrom(cls)) {
                    cn.jiguang.ac.d.b("JMessenger", "Receiver onReceive," + str + "." + str2);
                    ((BroadcastReceiver) cls.newInstance()).onReceive(context, intent);
                } else {
                    cn.jiguang.ac.d.b("JMessenger", "Service onStartCommand," + str + "." + str2);
                    ((Service) cls.newInstance()).onStartCommand(intent, 0, 0);
                }
                return new Bundle();
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.h("JMessenger", "directHandle e:" + th);
            return null;
        }
    }

    private static Bundle b(Context context, String str, String str2, Bundle bundle, String str3) {
        Map<String, String> map;
        String str4 = str + "." + str2;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                String str5 = b.get(str3);
                if (TextUtils.isEmpty(str5)) {
                    String str6 = context.getPackageName() + ".DataProvider";
                    ProviderInfo a2 = cn.jiguang.as.a.a(context, context.getPackageName(), str6);
                    if (str3.equals(a2 != null ? a2.processName : null)) {
                        str5 = "content://" + str6 + "/";
                        map = b;
                    } else {
                        String str7 = context.getPackageName() + ".DownloadProvider";
                        ProviderInfo a3 = cn.jiguang.as.a.a(context, context.getPackageName(), str7);
                        if (str3.equals(a3 != null ? a3.processName : null)) {
                            str5 = "content://" + str7 + "/";
                            map = b;
                        } else {
                            str5 = "";
                        }
                    }
                    map.put(str3, str5);
                }
                if (!TextUtils.isEmpty(str5)) {
                    Uri parse = Uri.parse(str5);
                    cn.jiguang.ac.d.b("JMessenger", "sendByProvider, uri=" + str5 + ", " + str4);
                    return context.getContentResolver().call(parse, str, str2, bundle);
                }
                cn.jiguang.ac.d.b("JMessenger", "uri is null, check provider config");
            }
        } catch (Throwable th) {
            cn.jiguang.ac.d.f("JMessenger", "provider call:" + th);
        }
        return null;
    }

    public final Bundle a(Context context, String str, String str2, Bundle bundle, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return null;
        }
        String a2 = cn.jiguang.as.a.a(context);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        if (a2.equals(str3)) {
            return b(context, str, str2, bundle);
        }
        Bundle a3 = a(str, str2, bundle, str3);
        if (a3 != null) {
            return a3;
        }
        Bundle b2 = b(context, str, str2, bundle, str3);
        if (b2 != null) {
            return b2;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0066 A[Catch: all -> 0x006d, TRY_LEAVE, TryCatch #1 {all -> 0x006d, blocks: (B:18:0x005e, B:21:0x0066), top: B:29:0x005e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.content.Context r11, java.lang.String r12, java.lang.String r13, android.os.Bundle r14) {
        /*
            r10 = this;
            java.lang.String r7 = " meet e:"
            java.lang.String r8 = "JMessenger"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r0 = r0.append(r12)
            java.lang.String r1 = "."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r13)
            java.lang.String r9 = r0.toString()
            r1 = 0
            java.lang.Class r2 = java.lang.Class.forName(r12)     // Catch: java.lang.Throwable -> L3d
            java.lang.Class<android.app.Service> r0 = android.app.Service.class
            boolean r0 = r0.isAssignableFrom(r2)     // Catch: java.lang.Throwable -> L3b
            if (r0 == 0) goto L2e
            java.lang.String r0 = cn.jiguang.as.a.a(r11, r12)     // Catch: java.lang.Throwable -> L3b
            r6 = r0
            goto L58
        L2e:
            java.lang.Class<android.content.BroadcastReceiver> r0 = android.content.BroadcastReceiver.class
            boolean r0 = r0.isAssignableFrom(r2)     // Catch: java.lang.Throwable -> L3b
            if (r0 == 0) goto L57
            java.lang.String r1 = cn.jiguang.as.a.b(r11, r12)     // Catch: java.lang.Throwable -> L3b
            goto L57
        L3b:
            r0 = move-exception
            goto L3f
        L3d:
            r0 = move-exception
            r2 = r1
        L3f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r0 = r0.toString()
            cn.jiguang.ac.d.b(r8, r0)
        L57:
            r6 = r1
        L58:
            r0 = r2
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            android.os.Bundle r1 = r1.a(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L6d
            r2 = 1
            if (r1 == 0) goto L66
            return r2
        L66:
            boolean r0 = a(r11, r0, r13, r14)     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L88
            return r2
        L6d:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "send, "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.StringBuilder r1 = r1.append(r7)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            cn.jiguang.ac.d.f(r8, r0)
        L88:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "send all failed, "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.String r0 = r0.toString()
            cn.jiguang.ac.d.h(r8, r0)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ab.g.a(android.content.Context, java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }
}
