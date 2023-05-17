package cn.jiguang.af;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class q implements Callable<n> {
    private final l a;
    private final r b;
    private final g c;
    private Set<String> d;
    private h e;

    public q(l lVar, h hVar, Set<String> set) {
        this.a = lVar;
        this.b = null;
        this.c = null;
        this.e = hVar;
        this.d = set;
    }

    public q(l lVar, r rVar, g gVar) {
        this.a = lVar;
        this.b = rVar;
        this.c = gVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public n call() {
        try {
            if (this.c != null) {
                return a(this.c.a(this.a.a()));
            }
            return a(this.e);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("SisTask", "run e:" + th);
            return null;
        }
    }

    private n a(h hVar) {
        if (hVar != null && hVar.c != null) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                if (!TextUtils.isEmpty(cn.jiguang.ae.a.a)) {
                    hVar.c = InetAddress.getByName(cn.jiguang.ae.a.a);
                }
                if (cn.jiguang.ae.a.b > 0) {
                    hVar.b = cn.jiguang.ae.a.b;
                }
                cn.jiguang.ai.a.c("SisTask", "send sis:" + hVar.c + " port:" + hVar.b);
                Object[] a = a(datagramSocket, hVar.c, hVar.b);
                if (a[0] instanceof n) {
                    boolean z = hVar.c instanceof Inet4Address;
                    this.a.a(z);
                    cn.jiguang.ae.c.a(this.a.a, cn.jiguang.ae.b.a(z).a((cn.jiguang.ae.b<String>) hVar.toString()));
                    r rVar = this.b;
                    if (rVar != null) {
                        rVar.a(a[0]);
                    }
                    return (n) a[0];
                }
                int intValue = ((Integer) a[0]).intValue();
                long longValue = ((Long) a[1]).longValue();
                cn.jiguang.ai.a.g("SisTask", "sis failed(" + intValue + "):" + hVar.c + " port:" + hVar.b);
                this.a.a(1, hVar.c.getHostAddress(), hVar.b, cn.jiguang.sdk.impl.b.j(this.a.a), longValue, intValue);
            } catch (Throwable th) {
                cn.jiguang.ai.a.g("SisTask", "sis e:" + th);
            }
        }
        return null;
    }

    private Object[] a(DatagramSocket datagramSocket, InetAddress inetAddress, int i) {
        Object[] objArr = new Object[2];
        objArr[1] = 0;
        try {
            byte[] a = this.a.a(this.d);
            DatagramPacket datagramPacket = new DatagramPacket(a, a.length, inetAddress, i);
            long uptimeMillis = SystemClock.uptimeMillis();
            try {
                byte[] a2 = c.a(datagramSocket, datagramPacket);
                objArr[1] = Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                try {
                    n nVar = new n(new String(c.a(a2)));
                    if (nVar.a()) {
                        objArr[0] = 6;
                        return objArr;
                    }
                    Context context = this.a.a;
                    if (!nVar.a()) {
                        String a3 = h.a(nVar.a);
                        String a4 = h.a(nVar.b);
                        String c = cn.jiguang.ap.i.c(context);
                        cn.jiguang.ai.a.c("SisTask", "updateSisInfo ips=" + a3 + " sslIps=" + a4 + " net=" + c);
                        cn.jiguang.ae.b[] bVarArr = new cn.jiguang.ae.b[8];
                        bVarArr[0] = cn.jiguang.ae.b.p().a((cn.jiguang.ae.b<String>) a3);
                        bVarArr[1] = cn.jiguang.ae.b.q().a((cn.jiguang.ae.b<String>) a4);
                        bVarArr[2] = cn.jiguang.ae.b.c(false).a((cn.jiguang.ae.b<String>) h.a(nVar.c));
                        bVarArr[3] = cn.jiguang.ae.b.c(true).a((cn.jiguang.ae.b<String>) h.a(nVar.d));
                        bVarArr[4] = cn.jiguang.ae.b.u().a((cn.jiguang.ae.b<String>) h.a(nVar.e));
                        bVarArr[5] = cn.jiguang.ae.b.x().a((cn.jiguang.ae.b<String>) (nVar.f != null ? nVar.f.toString() : null));
                        bVarArr[6] = cn.jiguang.ae.b.r().a((cn.jiguang.ae.b<Boolean>) Boolean.valueOf(nVar.h));
                        bVarArr[7] = cn.jiguang.ae.b.s().a((cn.jiguang.ae.b<Long>) Long.valueOf(SystemClock.elapsedRealtime()));
                        cn.jiguang.ae.c.a(context, bVarArr);
                        cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.k().a((cn.jiguang.ae.b<String>) c));
                    }
                    nVar.g = new h(inetAddress, i);
                    objArr[0] = nVar;
                    return objArr;
                } catch (cn.jiguang.ah.f e) {
                    objArr[0] = Integer.valueOf(e.a);
                    return objArr;
                }
            } catch (Exception unused) {
                objArr[0] = 3;
                objArr[1] = Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis);
                return objArr;
            }
        } catch (cn.jiguang.ah.f e2) {
            objArr[0] = Integer.valueOf(e2.a);
            return objArr;
        }
    }
}
