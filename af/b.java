package cn.jiguang.af;

import android.os.SystemClock;
import android.text.TextUtils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class b implements Callable<cn.jiguang.an.a> {
    private final l a;
    private final r b;
    private final g c;
    private final i d;

    public b(l lVar, r rVar, g gVar, i iVar) {
        this.b = rVar;
        this.a = lVar;
        this.c = gVar;
        this.d = iVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public cn.jiguang.an.a call() {
        boolean z;
        try {
            if (this.c != null) {
                h a = this.c.a(this.a.a());
                if (this.b.a()) {
                    return null;
                }
                i iVar = this.d;
                if (iVar != null && !iVar.a) {
                    if (a == null) {
                        return null;
                    }
                    if (!TextUtils.isEmpty(cn.jiguang.ae.a.c)) {
                        a.a = cn.jiguang.ae.a.c;
                        a.c = InetAddress.getByName(cn.jiguang.ae.a.c);
                    }
                    if (cn.jiguang.ae.a.d > 0) {
                        a.b = cn.jiguang.ae.a.d;
                    }
                    cn.jiguang.ai.a.d("ConnTask", "Open connection with ip=" + a.c + ", port:" + a.b);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    cn.jiguang.an.b bVar = new cn.jiguang.an.b(8128, 20);
                    int a2 = bVar.a(a.a, a.b);
                    if (this.b.a()) {
                        cn.jiguang.ap.k.a(bVar);
                        return null;
                    } else if (this.d.a) {
                        this.b.a(new cn.jiguang.ah.f(-991, null));
                        cn.jiguang.ap.k.a(bVar);
                        return null;
                    } else if (a2 != 0) {
                        long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
                        this.a.a(2, a.a, a.b, cn.jiguang.sdk.impl.b.j(this.a.a), uptimeMillis2, a2);
                        cn.jiguang.ai.a.e("ConnTask", "Failed(" + a2 + ") to open connection - ip:" + a.c + ", port:" + a.b + ", cost:" + uptimeMillis2);
                        e.a(this.a.a, a, -1, uptimeMillis2);
                        cn.jiguang.ap.k.a(bVar);
                        return null;
                    } else {
                        if (!(a.c instanceof Inet4Address) && !cn.jiguang.ap.h.f(a.a)) {
                            z = false;
                            cn.jiguang.ae.c.a(this.a.a, cn.jiguang.ae.b.b(z).a((cn.jiguang.ae.b<String>) a.toString()));
                            cn.jiguang.ai.a.f("ConnTask", "Succeed to open connection - ip:" + a.c + ", port:" + a.b);
                            this.b.a(bVar);
                            e.a(this.a.a, a, 1, 0L);
                            return bVar;
                        }
                        z = true;
                        cn.jiguang.ae.c.a(this.a.a, cn.jiguang.ae.b.b(z).a((cn.jiguang.ae.b<String>) a.toString()));
                        cn.jiguang.ai.a.f("ConnTask", "Succeed to open connection - ip:" + a.c + ", port:" + a.b);
                        this.b.a(bVar);
                        e.a(this.a.a, a, 1, 0L);
                        return bVar;
                    }
                }
                this.b.a(new cn.jiguang.ah.f(-991, null));
                return null;
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("ConnTask", "run e:" + th);
        }
        return null;
    }
}
