package cn.jiguang.j;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import cn.jiguang.s.d;
import com.facebook.internal.security.CertificateUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    private static String a = "JAppHelper";

    public static int a(ApplicationInfo applicationInfo) {
        if (applicationInfo == null) {
            return -1;
        }
        try {
            if ((applicationInfo.flags & 1) != 0) {
                return (applicationInfo.flags & 128) != 0 ? 2 : 1;
            }
            String str = applicationInfo.sourceDir;
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            return (str.startsWith("/system/") || !str.contains(applicationInfo.packageName)) ? 3 : 0;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g(a, "getAppInstalledType throwable:" + th.getMessage());
            return -1;
        }
    }

    public static ApplicationInfo a(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g(a, "getApplicationInfo throwable:" + th.getMessage());
            return null;
        }
    }

    public static String a(List<cn.jiguang.i.a> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).b);
            if (i != list.size() - 1) {
                sb.append("&&");
            }
        }
        return sb.toString();
    }

    public static String a(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        for (String str : set) {
            sb.append(str);
            sb.append("&&");
        }
        return sb.toString();
    }

    public static ArrayList<JSONArray> a(JSONArray jSONArray) {
        String str;
        StringBuilder sb;
        String message;
        if (jSONArray != null && jSONArray.length() != 0) {
            try {
                JSONArray jSONArray2 = new JSONArray();
                ArrayList<JSONArray> arrayList = new ArrayList<>();
                int i = 0;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (optJSONObject != null && optJSONObject.length() != 0) {
                        int length = optJSONObject.toString().getBytes("UTF-8").length;
                        i += length;
                        if (i > 102400) {
                            if (jSONArray2.length() > 0) {
                                arrayList.add(jSONArray2);
                            }
                            jSONArray2 = new JSONArray();
                            jSONArray2.put(optJSONObject);
                            i = length;
                        } else {
                            jSONArray2.put(optJSONObject);
                        }
                    }
                }
                if (jSONArray2.length() > 0) {
                    arrayList.add(jSONArray2);
                }
                return arrayList;
            } catch (UnsupportedEncodingException e) {
                str = a;
                sb = new StringBuilder("partition exception:");
                message = e.getMessage();
                cn.jiguang.ai.a.g(str, sb.append(message).toString());
                return null;
            } catch (Throwable th) {
                str = a;
                sb = new StringBuilder("partition throwable:");
                message = th.getMessage();
                cn.jiguang.ai.a.g(str, sb.append(message).toString());
                return null;
            }
        }
        return null;
    }

    public static List<cn.jiguang.i.a> a(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return b(context);
        }
        HashMap hashMap = new HashMap();
        for (cn.jiguang.i.b bVar : b.a(1)) {
            cn.jiguang.i.a b = b(context, bVar.d);
            if (b != null) {
                hashMap.put(b.b, b);
            }
        }
        return new ArrayList(hashMap.values());
    }

    public static List<cn.jiguang.i.a> a(Context context, boolean z) {
        List<PackageInfo> a2;
        PackageManager packageManager = context.getPackageManager();
        try {
            cn.jiguang.ai.a.c(a, "getInstalledApps by api");
            a2 = packageManager.getInstalledPackages(0);
        } catch (Throwable unused) {
            cn.jiguang.ai.a.c(a, "getInstalledApps by shell");
            a2 = a(packageManager);
        }
        if (a2 == null || a2.isEmpty()) {
            return null;
        }
        return a(packageManager, a2, true);
    }

    private static List<PackageInfo> a(PackageManager packageManager) {
        try {
            List<String> a2 = d.a(new String[]{"pm list package"}, 1);
            if (a2 != null && !a2.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < a2.size(); i++) {
                    String str = a2.get(i);
                    if (!TextUtils.isEmpty(str) && str.startsWith("package:")) {
                        String substring = str.substring(8);
                        cn.jiguang.ai.a.c(a, "execute command packageName:" + substring);
                        if (!TextUtils.isEmpty(substring)) {
                            PackageInfo packageInfo = new PackageInfo();
                            try {
                                packageInfo = packageManager.getPackageInfo(substring, 64);
                            } catch (PackageManager.NameNotFoundException unused) {
                                packageInfo.packageName = substring;
                            }
                            arrayList.add(packageInfo);
                        }
                    }
                }
                return arrayList;
            }
            cn.jiguang.ai.a.g(a, "execute command pm list package failed");
            return null;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g(a, "getInstalledPackagesByShell throwable:" + th.getMessage());
            return null;
        }
    }

    private static List<cn.jiguang.i.a> a(PackageManager packageManager, List<PackageInfo> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            try {
                PackageInfo packageInfo = list.get(i);
                if (z || packageInfo.versionName != null) {
                    cn.jiguang.i.a aVar = new cn.jiguang.i.a();
                    aVar.a = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                    aVar.b = packageInfo.packageName;
                    aVar.c = packageInfo.versionName;
                    aVar.d = packageInfo.versionCode;
                    aVar.e = a(packageInfo.applicationInfo);
                    arrayList.add(aVar);
                }
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }

    public static Set<String> a(String str) {
        String[] split = str.split("&&");
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, split);
        return hashSet;
    }

    private static cn.jiguang.i.a b(Context context, String str) {
        try {
            PackageInfo c = c(context, str);
            if (c == null) {
                cn.jiguang.ai.a.g(a, "getAppInfoFromPackage failed because packageInfo is null");
                return null;
            }
            String charSequence = c.applicationInfo.loadLabel(context.getPackageManager()).toString();
            cn.jiguang.i.a aVar = new cn.jiguang.i.a();
            aVar.a = b(charSequence);
            aVar.b = c.packageName;
            aVar.d = c.versionCode;
            aVar.c = c.versionName;
            aVar.e = a(c.applicationInfo);
            return aVar;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g(a, "getAppInfoFromPackage throwable:" + th.getMessage());
            return null;
        }
    }

    private static String b(String str) {
        String str2;
        StringBuilder sb;
        String message;
        if (!TextUtils.isEmpty(str)) {
            String replaceAll = Pattern.compile("\n|\r|\r\n|\n\r|\t").matcher(str).replaceAll("");
            try {
                byte[] bytes = str.getBytes();
                if (bytes.length > 30) {
                    return replaceAll.substring(0, new String(bytes, 0, 30, "UTF-8").length());
                }
            } catch (UnsupportedEncodingException e) {
                str2 = a;
                sb = new StringBuilder("getAppName exception:");
                message = e.getMessage();
                cn.jiguang.ai.a.g(str2, sb.append(message).toString());
                return str;
            } catch (Throwable th) {
                str2 = a;
                sb = new StringBuilder("getAppName throwable:");
                message = th.getMessage();
                cn.jiguang.ai.a.g(str2, sb.append(message).toString());
                return str;
            }
        }
        return str;
    }

    private static List<cn.jiguang.i.a> b(Context context) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            HashSet hashSet = new HashSet();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                String[] strArr = runningAppProcessInfo.pkgList;
                if (strArr != null && strArr.length > 0) {
                    Collections.addAll(hashSet, strArr);
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                cn.jiguang.i.a b = b(context, (String) it.next());
                if (b != null) {
                    arrayList.add(b);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            cn.jiguang.ai.a.g(a, "getRunningAppInfoBelowL throwable:" + th.getMessage());
            return null;
        }
    }

    private static PackageInfo c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Throwable unused) {
            try {
                int indexOf = str.indexOf(CertificateUtil.DELIMITER);
                if (indexOf <= 0) {
                    return null;
                }
                return context.getPackageManager().getPackageInfo(str.substring(0, indexOf), 0);
            } catch (Throwable th) {
                cn.jiguang.ai.a.g(a, "getPackageInfo throwable:" + th.getMessage());
                return null;
            }
        }
    }
}
