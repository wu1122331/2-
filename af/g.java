package cn.jiguang.af;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes.dex */
public final class g {
    private LinkedHashSet<h> a = new LinkedHashSet<>();
    private LinkedHashSet<h> b = new LinkedHashSet<>();
    private List<Boolean> c = new ArrayList();

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0053, code lost:
        if (r2 != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private cn.jiguang.af.h a(boolean r7, boolean r8) {
        /*
            r6 = this;
            if (r7 == 0) goto L5
            java.util.LinkedHashSet<cn.jiguang.af.h> r0 = r6.b
            goto L7
        L5:
            java.util.LinkedHashSet<cn.jiguang.af.h> r0 = r6.a
        L7:
            if (r8 == 0) goto Lb
            r8 = 0
            goto L12
        Lb:
            if (r7 == 0) goto L10
            java.util.LinkedHashSet<cn.jiguang.af.h> r8 = r6.a
            goto L12
        L10:
            java.util.LinkedHashSet<cn.jiguang.af.h> r8 = r6.b
        L12:
            if (r8 == 0) goto L5c
            boolean r1 = r8.isEmpty()
            if (r1 == 0) goto L1b
            goto L5c
        L1b:
            if (r0 == 0) goto L55
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L24
            goto L55
        L24:
            java.util.List<java.lang.Boolean> r1 = r6.c
            int r1 = r1.size()
            r2 = 0
            r3 = 1
            r4 = 2
            if (r1 >= r4) goto L30
            goto L53
        L30:
            java.util.List<java.lang.Boolean> r1 = r6.c
            int r1 = r1.size()
            int r1 = r1 - r3
        L37:
            java.util.List<java.lang.Boolean> r5 = r6.c
            int r5 = r5.size()
            int r5 = r5 - r4
            if (r1 < r5) goto L52
            java.util.List<java.lang.Boolean> r5 = r6.c
            java.lang.Object r5 = r5.get(r1)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == r7) goto L4f
            goto L53
        L4f:
            int r1 = r1 + (-1)
            goto L37
        L52:
            r2 = 1
        L53:
            if (r2 == 0) goto L5c
        L55:
            java.lang.Object r7 = a(r8)
        L59:
            cn.jiguang.af.h r7 = (cn.jiguang.af.h) r7
            return r7
        L5c:
            java.lang.Object r7 = a(r0)
            goto L59
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.g.a(boolean, boolean):cn.jiguang.af.h");
    }

    private static <T> T a(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        Iterator<T> it = collection.iterator();
        if (it.hasNext()) {
            T next = it.next();
            collection.remove(next);
            return next;
        }
        return null;
    }

    public final h a(int i) {
        cn.jiguang.ai.a.c("IpPool", "current ipv4List=" + this.a + " ipv6List=" + this.b);
        h a = i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : a(true, false) : a(true, true) : a(false, true) : a(false, false);
        cn.jiguang.ai.a.c("IpPool", "get ipPort=" + a);
        if (a != null) {
            if (a.c instanceof Inet4Address) {
                this.c.add(false);
            } else if (a.c instanceof Inet6Address) {
                this.c.add(true);
            }
        }
        return a;
    }

    public final void a() {
        try {
            this.a.clear();
            this.b.clear();
        } catch (Throwable unused) {
        }
    }

    public final boolean a(h hVar) {
        LinkedHashSet<h> linkedHashSet;
        if (hVar.a()) {
            if (hVar.c instanceof Inet4Address) {
                linkedHashSet = this.a;
            } else if (!(hVar.c instanceof Inet6Address)) {
                return false;
            } else {
                linkedHashSet = this.b;
            }
            return linkedHashSet.add(hVar);
        }
        return false;
    }
}
