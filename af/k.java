package cn.jiguang.af;

import android.text.TextUtils;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class k {
    private l b;
    private i c;
    private q f;
    private LinkedHashSet<h> a = new LinkedHashSet<>();
    private r d = new r(5, 2000, null);
    private g e = new g();

    public k(l lVar) {
        this.b = lVar;
    }

    private void a() {
        h a = h.a((String) cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.b(true)));
        cn.jiguang.ai.a.c("SisConn", "connect: use last good v4 address=" + a);
        if (a(a)) {
            return;
        }
        h a2 = h.a((String) cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.b(false)));
        cn.jiguang.ai.a.c("SisConn", "connect: use last good v6 address=" + a2);
        if (a(a2)) {
            return;
        }
        LinkedHashSet<h> b = b();
        if (b != null) {
            b.removeAll(this.a);
        }
        LinkedList a3 = cn.jiguang.ap.k.a(b);
        cn.jiguang.ai.a.c("SisConn", "connect: use defaultConn=" + a3);
        Iterator it = a3.iterator();
        while (it.hasNext()) {
            if (a((h) it.next())) {
                return;
            }
        }
        LinkedHashSet<h> a4 = cn.jiguang.ag.m.a().a(cn.jiguang.ae.a.b(this.b.a), 10000L);
        a3.clear();
        if (a4 != null) {
            a4.removeAll(this.a);
            a3 = cn.jiguang.ap.k.a(a4);
        }
        cn.jiguang.ai.a.c("SisConn", "connect: use srv address" + a3);
        Iterator it2 = a3.iterator();
        while (it2.hasNext() && !a((h) it2.next())) {
        }
    }

    private void a(LinkedHashSet<h> linkedHashSet) {
        linkedHashSet.removeAll(this.a);
        if (linkedHashSet.isEmpty()) {
            return;
        }
        LinkedHashSet<h> a = e.a(this.b.a, linkedHashSet, 0L);
        cn.jiguang.ai.a.c("SisConn", "connect: last good sis info" + a);
        Iterator<h> it = a.iterator();
        while (it.hasNext() && !a(it.next())) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.util.LinkedHashSet<cn.jiguang.af.h> r13, long r14) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.k.a(java.util.LinkedHashSet, long):void");
    }

    private boolean a(h hVar) {
        InetAddress[] a;
        cn.jiguang.ah.f fVar;
        if (!this.c.a) {
            if (this.d.a()) {
                return true;
            }
            if (hVar != null && hVar.a() && !this.a.contains(hVar) && (a = cn.jiguang.aj.a.a().a(this.b.a, hVar.a, 3000L, false)) != null && a.length != 0) {
                Iterator it = cn.jiguang.ap.k.a(Arrays.asList(a)).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InetAddress inetAddress = (InetAddress) it.next();
                    if (!this.c.a) {
                        if (!this.d.a()) {
                            h hVar2 = new h(inetAddress, hVar.b);
                            if (!this.a.contains(hVar2) && this.e.a(hVar2)) {
                                this.a.add(hVar2);
                                this.d.a((Callable<?>) new b(this.b, this.d, this.e, this.c));
                                break;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        fVar = new cn.jiguang.ah.f(-991, null);
                        break;
                    }
                }
                this.a.add(hVar);
            }
            return false;
        }
        fVar = new cn.jiguang.ah.f(-991, null);
        this.d.a(fVar);
        return true;
    }

    private LinkedHashSet<h> b() {
        InetAddress[] a;
        try {
            String a2 = cn.jiguang.ae.a.a(this.b.a);
            cn.jiguang.ai.a.c("SisConn", "load Default Conn, from host=" + a2);
            if (!TextUtils.isEmpty(a2) && (a = cn.jiguang.aj.a.a().a(this.b.a, a2, 3000L, false)) != null && a.length != 0) {
                LinkedList a3 = cn.jiguang.ap.k.a(Arrays.asList(a));
                if (a3.isEmpty()) {
                    return null;
                }
                String hostAddress = ((InetAddress) a3.get(0)).getHostAddress();
                LinkedHashSet<h> linkedHashSet = new LinkedHashSet<>();
                linkedHashSet.add(new h(hostAddress, 7000));
                linkedHashSet.add(new h(hostAddress, 7002));
                linkedHashSet.add(new h(hostAddress, 7003));
                linkedHashSet.add(new h(hostAddress, 7004));
                linkedHashSet.add(new h(hostAddress, 7005));
                linkedHashSet.add(new h(hostAddress, 7006));
                linkedHashSet.add(new h(hostAddress, 7007));
                linkedHashSet.add(new h(hostAddress, 7008));
                linkedHashSet.add(new h(hostAddress, 7009));
                return linkedHashSet;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final cn.jiguang.an.a a(cn.jiguang.af.i r14) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.k.a(cn.jiguang.af.i):cn.jiguang.an.a");
    }
}
