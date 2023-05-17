package cn.jiguang.j;

import android.text.TextUtils;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class b {
    private static final Pattern a = Pattern.compile("^zygote[0-9]*$");

    private static int a(LinkedList<String> linkedList, String str, int i) {
        int indexOf = linkedList.indexOf(str);
        if (indexOf == -1) {
            indexOf = linkedList.indexOf(str.toLowerCase(Locale.ENGLISH));
        }
        return indexOf == -1 ? i : indexOf;
    }

    private static cn.jiguang.i.b a(String str, Map<String, Integer> map) {
        String[] split;
        int length;
        String str2;
        if (!TextUtils.isEmpty(str) && (length = (split = str.split("\\s+")).length) >= 3) {
            try {
                cn.jiguang.i.b bVar = new cn.jiguang.i.b();
                if (map != null && !map.isEmpty()) {
                    bVar.a = split[map.get("USER").intValue()];
                    bVar.b = split[map.get("PID").intValue()];
                    bVar.c = split[map.get("PPID").intValue()];
                    str2 = split[map.get("NAME").intValue()];
                    bVar.d = str2;
                    return bVar;
                }
                bVar.a = split[0];
                bVar.b = split[1];
                bVar.c = split[2];
                str2 = split[length - 1];
                bVar.d = str2;
                return bVar;
            } catch (Throwable th) {
                cn.jiguang.ai.a.g("JProcessHelper", "parseCommandResult throwable:" + th.getMessage());
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0077 A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0007, B:5:0x0012, B:8:0x001a, B:17:0x006d, B:18:0x0071, B:20:0x0077, B:24:0x0086, B:26:0x0090, B:28:0x0098, B:34:0x00a6, B:36:0x00b4, B:37:0x00ba, B:39:0x00c2, B:41:0x00c8, B:43:0x00ce, B:45:0x00d6, B:46:0x00db, B:48:0x00e1, B:50:0x00ef, B:11:0x0033, B:54:0x0100), top: B:59:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<cn.jiguang.i.b> a(int r16) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.j.b.a(int):java.util.List");
    }
}
