package cn.jiguang.af;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/* loaded from: classes.dex */
public final class h implements Serializable {
    public String a;
    public int b;
    public transient InetAddress c;

    public h(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public h(InetAddress inetAddress, int i) {
        this.a = inetAddress.getHostAddress();
        this.b = i;
        this.c = inetAddress;
    }

    public static h a(String str) {
        int lastIndexOf;
        String substring;
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(CertificateUtil.DELIMITER)) >= 0) {
            try {
                int intValue = Integer.decode(str.substring(lastIndexOf + 1)).intValue();
                if (lastIndexOf == 0) {
                    substring = "";
                } else {
                    int i = str.startsWith("[") ? 1 : 0;
                    if (str.charAt(lastIndexOf - 1) == ']') {
                        lastIndexOf--;
                    }
                    substring = str.substring(i, lastIndexOf);
                }
                return new h(substring, intValue);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String a(LinkedHashSet<h> linkedHashSet) {
        if (linkedHashSet == null || linkedHashSet.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<h> it = linkedHashSet.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static LinkedHashSet<h> a(LinkedHashMap<String, Integer> linkedHashMap) {
        LinkedHashSet<h> linkedHashSet = new LinkedHashSet<>();
        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            h hVar = new h(entry.getKey(), entry.getValue().intValue());
            if (hVar.a()) {
                linkedHashSet.add(hVar);
            }
        }
        return linkedHashSet;
    }

    public static boolean a(String str, int i) {
        return !TextUtils.isEmpty(str) && i > 0 && i <= 65535;
    }

    public static LinkedHashSet<h> b(String str) {
        LinkedHashSet<h> linkedHashSet = new LinkedHashSet<>();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                h a = a(str2);
                if (a != null && a.a()) {
                    linkedHashSet.add(a);
                }
            }
        }
        return linkedHashSet;
    }

    public final boolean a() {
        int i;
        return !TextUtils.isEmpty(this.a) && (i = this.b) > 0 && i <= 65535;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            h hVar = (h) obj;
            if (this.b == hVar.b) {
                String str = this.a;
                String str2 = hVar.a;
                if (str == null ? str2 == null : str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        return ((str != null ? str.hashCode() : 0) * 31) + this.b;
    }

    public final String toString() {
        StringBuilder append;
        String str = this.a;
        String str2 = CertificateUtil.DELIMITER;
        if (str.contains(CertificateUtil.DELIMITER)) {
            append = new StringBuilder("[").append(this.a);
            str2 = "]:";
        } else {
            append = new StringBuilder().append(this.a);
        }
        return append.append(str2).append(this.b).toString();
    }
}
