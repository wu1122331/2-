package cn.jiguang.af;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class j implements Callable<n> {
    private LinkedHashSet<h> a = new LinkedHashSet<>();
    private l b;

    public j(l lVar) {
        this.b = lVar;
    }

    private void a(r rVar, g gVar) {
        LinkedHashSet<h> b = h.b((String) cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.u()));
        if (b.isEmpty()) {
            b = h.a(cn.jiguang.ae.a.b());
        }
        b.removeAll(this.a);
        LinkedList<h> a = cn.jiguang.ap.k.a(b);
        cn.jiguang.ai.a.c("Sis", "main sis: default sis" + a);
        for (h hVar : a) {
            if (a(hVar, rVar, gVar)) {
                return;
            }
        }
        LinkedHashSet<h> a2 = cn.jiguang.ag.m.a().a(cn.jiguang.ae.a.c(), 10000L);
        a.clear();
        if (a2 != null) {
            a2.removeAll(this.a);
            a = cn.jiguang.ap.k.a(a2);
        }
        cn.jiguang.ai.a.c("Sis", "main sis: sis srv" + a);
        Iterator it = a.iterator();
        while (it.hasNext() && !a((h) it.next(), rVar, gVar)) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0074, code lost:
        r9.a.add(r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(cn.jiguang.af.h r10, cn.jiguang.af.r r11, cn.jiguang.af.g r12) {
        /*
            r9 = this;
            boolean r0 = r11.a()
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = 0
            if (r10 == 0) goto L79
            boolean r2 = r10.a()
            if (r2 == 0) goto L79
            java.util.LinkedHashSet<cn.jiguang.af.h> r2 = r9.a
            boolean r2 = r2.contains(r10)
            if (r2 == 0) goto L1a
            goto L79
        L1a:
            cn.jiguang.aj.a r3 = cn.jiguang.aj.a.a()
            cn.jiguang.af.l r2 = r9.b
            android.content.Context r4 = r2.a
            java.lang.String r5 = r10.a
            r6 = 3000(0xbb8, double:1.482E-320)
            r8 = 0
            java.net.InetAddress[] r2 = r3.a(r4, r5, r6, r8)
            if (r2 == 0) goto L79
            int r3 = r2.length
            if (r3 != 0) goto L31
            goto L79
        L31:
            java.util.List r2 = java.util.Arrays.asList(r2)
            java.util.LinkedList r2 = cn.jiguang.ap.k.a(r2)
            java.util.Iterator r2 = r2.iterator()
        L3d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L74
            java.lang.Object r3 = r2.next()
            java.net.InetAddress r3 = (java.net.InetAddress) r3
            boolean r4 = r11.a()
            if (r4 == 0) goto L50
            return r1
        L50:
            cn.jiguang.af.h r4 = new cn.jiguang.af.h
            int r5 = r10.b
            r4.<init>(r3, r5)
            java.util.LinkedHashSet<cn.jiguang.af.h> r3 = r9.a
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L3d
            boolean r3 = r12.a(r4)
            if (r3 == 0) goto L3d
            java.util.LinkedHashSet<cn.jiguang.af.h> r1 = r9.a
            r1.add(r4)
            cn.jiguang.af.q r1 = new cn.jiguang.af.q
            cn.jiguang.af.l r2 = r9.b
            r1.<init>(r2, r11, r12)
            r11.a(r1)
        L74:
            java.util.LinkedHashSet<cn.jiguang.af.h> r11 = r9.a
            r11.add(r10)
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.j.a(cn.jiguang.af.h, cn.jiguang.af.r, cn.jiguang.af.g):boolean");
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ n call() {
        g gVar = new g();
        r rVar = new r(5, 2000, null);
        LinkedHashSet<h> a = h.a(cn.jiguang.ae.a.a());
        a.removeAll(this.a);
        LinkedList a2 = cn.jiguang.ap.k.a(a);
        cn.jiguang.ai.a.c("Sis", "main sis: sis host=" + a2);
        Iterator it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                h a3 = h.a((String) cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.a(true)));
                cn.jiguang.ai.a.c("Sis", "main sis: last good sis v4 address=" + a3);
                if (!a(a3, rVar, gVar)) {
                    h a4 = h.a((String) cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.a(false)));
                    cn.jiguang.ai.a.c("Sis", "main sis: last good sis v6 address=" + a4);
                    a(a4, rVar, gVar);
                }
            } else if (a((h) it.next(), rVar, gVar)) {
                break;
            }
        }
        cn.jiguang.ai.a.c("Sis", "main sis: after host and last good, wait Result");
        Object a5 = rVar.a(60000L);
        if (a5 instanceof n) {
            return (n) a5;
        }
        a(rVar, gVar);
        cn.jiguang.ai.a.c("Sis", "main sis: after default and srv, wait Result");
        Object a6 = rVar.a(60000L);
        if (a6 instanceof n) {
            return (n) a6;
        }
        return null;
    }
}
