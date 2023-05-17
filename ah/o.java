package cn.jiguang.ah;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class o {
    private static volatile o a = null;
    private static final Object b = new Object();
    private static long e = 1;
    private cn.jiguang.ao.a d = new p(this);
    private Map<Long, c> c = new HashMap();

    private o() {
    }

    public static o a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new o();
                }
            }
        }
        return a;
    }

    private static byte[] a(Context context, c cVar) {
        return cn.jiguang.ak.b.a(context, cVar.d, cVar.e, cVar.f, cVar.g, 0L);
    }

    public static long b() {
        long j = e + 1;
        e = j;
        if (j >= 2147483647L) {
            e = 1L;
        }
        return e;
    }

    public final void a(long j) {
        c remove = this.c.remove(Long.valueOf(j));
        if (remove != null) {
            if (remove.j) {
                cn.jiguang.ao.b.a().b((int) (j + 100000));
            }
            cn.jiguang.ai.a.c("TcpRequestManager", "handle reponse :" + remove);
        }
    }

    public final void a(Context context) {
        if (this.c.isEmpty()) {
            cn.jiguang.ai.a.c("TcpRequestManager", "no cache request");
            return;
        }
        for (Map.Entry<Long, c> entry : this.c.entrySet()) {
            if (entry.getValue().j) {
                long nanoTime = System.nanoTime() - entry.getValue().h;
                if (entry.getValue().i - nanoTime >= 10000) {
                    entry.getValue().a++;
                    cn.jiguang.ai.a.c("TcpRequestManager", "send again:" + entry.getValue());
                    i.a().c().c().a(a(context, entry.getValue()));
                } else {
                    cn.jiguang.ai.a.c("TcpRequestManager", "shoud not send again by 10000ms,hasRequestTime:" + nanoTime + ",timeout:" + entry.getValue().i);
                }
            }
        }
    }

    public final void a(Context context, long j) {
        c remove = this.c.remove(Long.valueOf(j));
        if (remove == null) {
            cn.jiguang.ai.a.g("TcpRequestManager", "not found requst by rid:" + j);
            return;
        }
        cn.jiguang.ai.a.c("TcpRequestManager", "request time out:" + remove);
        b.a();
        b.a(context, remove.c, remove.b, remove.d);
    }

    public final void a(Context context, long j, int i, int i2, byte[] bArr, String str) {
        long b2 = cn.jiguang.af.c.b(context);
        if (this.c.containsKey(Long.valueOf(b2))) {
            cn.jiguang.ai.a.h("TcpRequestManager", "Generator same rid,not do this msg");
            return;
        }
        c cVar = new c(j, str, i, i2, b2, 0L, bArr);
        if (i.a().d()) {
            i.a().c().c().a(a(context, cVar));
        }
        this.c.put(Long.valueOf(b2), cVar);
    }

    public final void a(Context context, long j, int i, int i2, byte[] bArr, String str, long j2) {
        long j3;
        if (i == 10) {
            j3 = j;
        } else {
            long b2 = cn.jiguang.af.c.b(context);
            cn.jiguang.ai.a.c("TcpRequestManager", "Generator new rid:" + b2);
            if (this.c.containsKey(Long.valueOf(b2))) {
                cn.jiguang.ai.a.h("TcpRequestManager", "Generator same rid,not do this msg");
                return;
            }
            j3 = b2;
        }
        long j4 = j2 <= 0 ? 10000L : j2;
        long j5 = j4;
        c cVar = new c(j, str, i, i2, j3, j4, bArr);
        if (i.a().d()) {
            i.a().c().c().a(a(context, cVar));
        }
        cVar.h = System.nanoTime();
        this.c.put(Long.valueOf(j3), cVar);
        cn.jiguang.ao.b.a().b((int) (j3 + 100000), j5, this.d);
    }

    public final c b(long j) {
        return this.c.get(Long.valueOf(j));
    }
}
