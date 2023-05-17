package cn.jiguang.ag;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.internal.view.SupportMenu;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/* loaded from: classes.dex */
public final class m {
    private static volatile m a;
    private static final Object b = new Object();
    private long c = 86400000;
    private long d = 1800000;
    private final Map<String, Pair<LinkedHashSet<cn.jiguang.af.h>, Long>> e = new HashMap();

    private m() {
    }

    public static m a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new m();
                }
            }
        }
        return a;
    }

    public static LinkedHashSet<cn.jiguang.af.h> a(String str) {
        String[] a2;
        f fVar;
        j a3;
        i[] a4;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] b2 = f.a(j.a(h.a(h.a(str), h.a), 33, 1)).b(SupportMenu.USER_MASK);
            cn.jiguang.ai.a.c("SRVLoader", "srv host:" + str);
            LinkedHashSet<cn.jiguang.af.h> linkedHashSet = new LinkedHashSet<>();
            try {
                a2 = l.b().a();
            } catch (Throwable th) {
                cn.jiguang.ai.a.h("SRVLoader", "Get default ports error with Exception:" + th);
            }
            if (a2 != null && a2.length != 0) {
                LinkedHashSet<InetAddress> linkedHashSet2 = new LinkedHashSet();
                cn.jiguang.aj.a a5 = cn.jiguang.aj.a.a();
                int length = a2.length;
                int i = 0;
                while (i < length) {
                    int i2 = i;
                    InetAddress[] a6 = a5.a(null, a2[i], 3000L, false);
                    InetAddress inetAddress = (a6 == null || a6.length <= 0) ? null : a6[0];
                    if (inetAddress != null) {
                        linkedHashSet2.add(inetAddress);
                    }
                    i = i2 + 1;
                }
                for (InetAddress inetAddress2 : linkedHashSet2) {
                    try {
                        fVar = new f(q.a(null, new InetSocketAddress(inetAddress2, 53), b2, System.currentTimeMillis() + 1000));
                        a3 = fVar.a();
                    } catch (IOException e) {
                        cn.jiguang.ai.a.g("SRVLoader", "tcp send to " + inetAddress2.getHostAddress() + " err:" + e);
                    }
                    if (a3 == null) {
                        break;
                    }
                    for (i iVar : fVar.a(1)) {
                        if (iVar.b().e() == a3.e() && iVar.b().d() == a3.c() && iVar.b().b().equals(a3.b())) {
                            Iterator a7 = iVar.a();
                            while (a7.hasNext()) {
                                o oVar = (o) a7.next();
                                if (oVar.h() > 0) {
                                    String hVar = oVar.i().toString();
                                    if (!TextUtils.isEmpty(hVar)) {
                                        if (hVar.endsWith(".")) {
                                            hVar = hVar.substring(0, hVar.length() - 1);
                                        }
                                        cn.jiguang.af.h hVar2 = new cn.jiguang.af.h(hVar, oVar.h());
                                        if (hVar2.a()) {
                                            linkedHashSet.add(hVar2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return linkedHashSet;
            }
            return linkedHashSet;
        } catch (IOException e2) {
            cn.jiguang.ai.a.g("SRVLoader", "can't srv, create query:" + e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(m mVar, String str, Pair pair) {
        if (pair.first == null || ((LinkedHashSet) pair.first).size() <= 0 || pair.second == null) {
            return;
        }
        mVar.e.put(str, pair);
        StringBuilder sb = new StringBuilder();
        Iterator it = ((LinkedHashSet) pair.first).iterator();
        while (it.hasNext()) {
            sb.append(((cn.jiguang.af.h) it.next()).toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        cn.jiguang.ae.c.a((Context) null, cn.jiguang.ae.b.d(str).a((cn.jiguang.ae.b<String>) sb.toString()), cn.jiguang.ae.b.e(str).a((cn.jiguang.ae.b<Long>) pair.second));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:5|(1:66)(1:11)|(1:13)(1:65)|14|(4:16|(2:18|(4:20|(3:22|(2:26|27)|28)|31|32))(1:63)|33|(8:35|(1:37)|38|(4:40|(1:61)(1:44)|45|(1:47))(1:62)|48|49|50|(2:56|57)(2:54|55)))|64|(0)(0)|48|49|50|(1:52)|56|57) */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00eb, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ec, code lost:
        cn.jiguang.ai.a.g("SRVLoader", "run futureTask e:" + r0);
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.LinkedHashSet<cn.jiguang.af.h> a(java.lang.String r19, long r20) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ag.m.a(java.lang.String, long):java.util.LinkedHashSet");
    }
}
