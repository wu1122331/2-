package cn.jiguang.ah;

import android.content.Context;
import android.util.Pair;
import android.util.SparseArray;
import cn.jiguang.api.ReportCallBack;
import cn.jiguang.api.utils.ByteBufferUtils;
import java.io.Closeable;
import java.io.File;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class l {
    private static l a;
    private static int e;
    private final AtomicBoolean b = new AtomicBoolean(false);
    private final Map<cn.jiguang.af.h, Pair<Integer, cn.jiguang.an.a>> c = new HashMap();
    private final Map<cn.jiguang.af.h, SparseArray<n>> d = new ConcurrentHashMap();
    private Map<String, Set<cn.jiguang.af.h>> f = new HashMap();

    private l() {
    }

    private static synchronized int a(Context context) {
        int intValue;
        synchronized (l.class) {
            Integer num = (Integer) cn.jiguang.ae.c.b(context, cn.jiguang.ae.b.o());
            if (num == null) {
                num = Integer.valueOf(Math.abs(new SecureRandom().nextInt(ByteBufferUtils.ERROR_CODE)));
            }
            Integer valueOf = Integer.valueOf((num.intValue() + 1) % ByteBufferUtils.ERROR_CODE);
            cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.o().a((cn.jiguang.ae.b<Integer>) valueOf));
            intValue = valueOf.intValue();
        }
        return intValue;
    }

    public static l a() {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    a = new l();
                }
            }
        }
        return a;
    }

    public static n a(Context context, JSONObject jSONObject, byte[] bArr, int i, File file, Set<String> set, ReportCallBack reportCallBack) {
        n nVar = new n();
        nVar.b = jSONObject;
        nVar.a = i;
        nVar.c = bArr;
        nVar.d = file;
        nVar.e = set;
        nVar.f = a(context);
        nVar.j = reportCallBack;
        return nVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public n a(cn.jiguang.af.h hVar, int i) {
        SparseArray<n> sparseArray;
        if (hVar == null || (sparseArray = this.d.get(hVar)) == null) {
            return null;
        }
        n nVar = sparseArray.get(i);
        sparseArray.remove(i);
        return nVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x00eb, code lost:
        if (r7.f.containsKey(r1) == false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0112 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Set<cn.jiguang.af.h> a(android.content.Context r8, java.util.Set<java.lang.String> r9) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.ah.l.a(android.content.Context, java.util.Set):java.util.Set");
    }

    private void a(Context context, int i, cn.jiguang.an.a aVar, cn.jiguang.af.h hVar, n nVar) {
        byte[] bArr;
        byte[] bArr2 = nVar.c;
        int i2 = nVar.a;
        int i3 = nVar.f;
        if (bArr2 == null || bArr2.length == 0 || bArr2.length > 30683) {
            bArr = null;
        } else {
            cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(bArr2.length + 37);
            eVar.b(0);
            eVar.a(1);
            b.a();
            eVar.a(b.b());
            long longValue = ((Long) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.d())).longValue();
            eVar.b(longValue);
            String e2 = cn.jiguang.ap.h.e((String) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.f()));
            byte[] c = cn.jiguang.ap.h.c(longValue + e2 + cn.jiguang.ap.h.a(bArr2));
            if (c == null) {
                c = new byte[16];
            } else if (c.length != 16) {
                byte[] bArr3 = new byte[16];
                System.arraycopy(bArr2, 0, bArr3, 0, Math.min(c.length, 16));
                c = bArr3;
            }
            eVar.a(c);
            eVar.a(i2);
            eVar.b(i3);
            eVar.a(bArr2);
            eVar.b(eVar.a(), 0);
            bArr = eVar.b();
        }
        if (bArr == null || bArr.length == 0) {
            cn.jiguang.ai.a.c("TcpReporter", "package data failed, give up, data=" + nVar);
            a(context, nVar, -1);
            return;
        }
        cn.jiguang.ai.a.c("TcpReporter", "send tcp data, len=" + bArr.length + ", data=" + nVar);
        nVar.i = true;
        aVar.a(bArr);
        cn.jiguang.ao.b.a().b(60000 + i, 31000L, new m(context, hVar));
        cn.jiguang.ao.b.a().b(nVar.f + 50000, 30000L, new m(context, nVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, cn.jiguang.af.h hVar, int i) {
        int i2;
        Pair<Integer, cn.jiguang.an.a> remove = this.c.remove(hVar);
        if (remove != null) {
            i2 = ((Integer) remove.first).intValue();
            cn.jiguang.ap.k.a((Closeable) remove.second);
        } else {
            i2 = 0;
        }
        SparseArray<n> remove2 = this.d.remove(hVar);
        if (i == 0 && !cn.jiguang.ap.a.c(context)) {
            i = -2;
        }
        if (remove2 == null || remove2.size() <= 0) {
            if (i2 > 0) {
                cn.jiguang.ai.a.c("TcpReporter", "socket(" + i2 + ") at " + hVar + " is disconnected, no task left");
                return;
            }
            return;
        }
        cn.jiguang.ai.a.c("TcpReporter", (i == 0 ? new StringBuilder("socket(").append(i2).append(") at ").append(hVar).append(" is disconnected, go on send waiting request") : new StringBuilder("socket(").append(i2).append(") at ").append(hVar).append(" is disconnected, finish waiting request, code=").append(i)).toString());
        for (int i3 = 0; i3 < remove2.size(); i3++) {
            n valueAt = remove2.valueAt(i3);
            cn.jiguang.ao.b.a().b(valueAt.f + 50000);
            if (i == 0) {
                a(context, valueAt);
            } else {
                m mVar = new m(context, 3, valueAt);
                m.a(mVar, i);
                cn.jiguang.sdk.impl.b.a(mVar);
            }
        }
    }

    private void a(Context context, n nVar, int i) {
        SparseArray<n> sparseArray;
        if (nVar.g != null && (sparseArray = this.d.get(nVar.g)) != null) {
            sparseArray.remove(nVar.f);
        }
        cn.jiguang.ao.b.a().b(nVar.f + 50000);
        m mVar = new m(context, 3, nVar);
        m.a(mVar, i);
        cn.jiguang.sdk.impl.b.a(mVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(l lVar, Context context, cn.jiguang.af.h hVar) {
        cn.jiguang.an.a aVar;
        byte b;
        if (hVar == null) {
            return;
        }
        try {
            Pair<Integer, cn.jiguang.an.a> pair = lVar.c.get(hVar);
            if (pair != null && (aVar = (cn.jiguang.an.a) pair.second) != null && !aVar.a()) {
                cn.jiguang.ai.a.c("TcpReporter", "start tcp socket(" + pair.first + "):" + hVar);
                if (aVar.a(hVar.a, hVar.b) != 0) {
                    lVar.a(context, hVar, 0);
                } else {
                    cn.jiguang.sdk.impl.b.a(new m(context, 2, hVar));
                    m mVar = new m(context, hVar);
                    while (!lVar.b.get()) {
                        try {
                            ByteBuffer a2 = aVar.a(0);
                            try {
                                cn.jiguang.ai.a.c("TcpReporter", "Received bytes - len:" + a2.array().length);
                                b = a2.get(2);
                            } catch (Throwable th) {
                                cn.jiguang.ai.a.g("TcpReporter", "tcp reporter onReceive err:" + th);
                            }
                            if (b != 1 && b != 0) {
                                cn.jiguang.ai.a.g("TcpReporter", "wrong version");
                                cn.jiguang.ao.b.a().b(((Integer) pair.first).intValue() + 60000, 31000L, mVar);
                            }
                            short s = a2.getShort(15);
                            short s2 = a2.getShort(17);
                            cn.jiguang.ai.a.c("TcpReporter", "onResult seqId=" + ((int) s) + " code=" + ((int) s2));
                            cn.jiguang.ao.b.a().b(50000 + s);
                            n a3 = lVar.a(hVar, s);
                            if (a3 != null) {
                                if (s2 == 0) {
                                    m mVar2 = new m(context, 3, a3);
                                    m.a(mVar2, s2);
                                    cn.jiguang.sdk.impl.b.a(mVar2);
                                } else if (s2 == 401) {
                                    lVar.a(context, hVar, s2);
                                } else {
                                    lVar.a(context, a3);
                                }
                            }
                            cn.jiguang.ao.b.a().b(((Integer) pair.first).intValue() + 60000, 31000L, mVar);
                        } catch (f e2) {
                            if (e2.a != -997) {
                                cn.jiguang.ai.a.g("TcpReporter", "recv failed with error:" + e2);
                            }
                        }
                    }
                }
                lVar.a(context, hVar, 0);
                if (lVar.b.get()) {
                    cn.jiguang.ai.a.g("TcpReporter", "Break receiving by wantStop");
                } else {
                    cn.jiguang.ai.a.g("TcpReporter", "disconnected");
                }
            }
        } catch (Throwable th2) {
            cn.jiguang.ai.a.a("TcpReporter", "socket exception", th2);
        }
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject.length() == 0) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONArray optJSONArray = jSONObject.optJSONArray(next);
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        cn.jiguang.af.h a2 = cn.jiguang.af.h.a(optJSONArray.getString(i));
                        if (a2 != null && a2.a()) {
                            linkedHashSet.add(a2);
                        }
                    }
                }
                hashMap.put(next, linkedHashSet);
            }
            if (hashMap.isEmpty()) {
                return;
            }
            this.f = hashMap;
        } catch (JSONException unused) {
        }
    }

    private static synchronized int b() {
        int i;
        synchronized (l.class) {
            i = (e + 1) % ByteBufferUtils.ERROR_CODE;
            e = i;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(l lVar, Context context, cn.jiguang.af.h hVar) {
        cn.jiguang.an.a aVar;
        cn.jiguang.ai.a.c("TcpReporter", "socket at " + hVar + " is connected, deal with waiting request");
        SparseArray<n> sparseArray = lVar.d.get(hVar);
        Pair<Integer, cn.jiguang.an.a> pair = lVar.c.get(hVar);
        if (pair != null) {
            if (sparseArray != null && (aVar = (cn.jiguang.an.a) pair.second) != null && aVar.a()) {
                for (int i = 0; i < sparseArray.size(); i++) {
                    n valueAt = sparseArray.valueAt(i);
                    if (!valueAt.i) {
                        lVar.a(context, ((Integer) pair.first).intValue(), aVar, hVar, valueAt);
                    }
                }
            }
            cn.jiguang.ao.b.a().b(((Integer) pair.first).intValue() + 60000, 31000L, new m(context, hVar));
        }
    }

    public final void a(Context context, n nVar) {
        try {
            boolean z = nVar.h == null;
            if (z) {
                cn.jiguang.ao.b.a().a(context);
                Set<String> set = nVar.e;
                LinkedHashSet<cn.jiguang.af.h> linkedHashSet = new LinkedHashSet<>();
                cn.jiguang.af.h a2 = cn.jiguang.af.h.a(cn.jiguang.ae.a.d());
                if (a2 == null || !a2.a()) {
                    Set<cn.jiguang.af.h> a3 = a(context, set);
                    if (a3 != null) {
                        for (cn.jiguang.af.h hVar : a3) {
                            if (hVar != null && hVar.a()) {
                                linkedHashSet.add(hVar);
                            }
                        }
                    }
                } else {
                    linkedHashSet.add(a2);
                }
                cn.jiguang.ai.a.c("TcpReporter", "tcp report find urls=" + linkedHashSet);
                nVar.h = linkedHashSet;
                cn.jiguang.ai.a.c("TcpReporter", "tcp report begin=" + nVar);
            }
            if (!cn.jiguang.ap.a.c(context)) {
                a(context, nVar, -2);
                return;
            }
            if (nVar.h != null && !nVar.h.isEmpty()) {
                cn.jiguang.af.h next = nVar.h.iterator().next();
                nVar.g = next;
                nVar.h.remove(next);
                if (z) {
                    cn.jiguang.ao.b.a().b(nVar.f + 50000, 30000L, new m(context, nVar));
                }
                Pair<Integer, cn.jiguang.an.a> pair = this.c.get(next);
                cn.jiguang.an.a aVar = pair != null ? (cn.jiguang.an.a) pair.second : null;
                SparseArray<n> sparseArray = this.d.get(next);
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                    this.d.put(next, sparseArray);
                }
                nVar.i = false;
                sparseArray.put(nVar.f, nVar);
                if (aVar == null) {
                    this.c.put(next, new Pair<>(Integer.valueOf(b()), new cn.jiguang.an.b(30720, 19)));
                    cn.jiguang.sdk.impl.b.a("TCP_REPORT", new m(context, 1, next));
                    return;
                } else if (aVar.a()) {
                    a(context, ((Integer) pair.first).intValue(), aVar, next, nVar);
                    return;
                } else {
                    return;
                }
            }
            a(context, nVar, -1);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("TcpReporter", "tcp upload e:" + th);
        }
    }
}
