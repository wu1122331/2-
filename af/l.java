package cn.jiguang.af;

import android.content.Context;
import android.os.Bundle;
import cn.jiguang.api.JCoreManager;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.UByte;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class l {
    private static l c;
    Context a;
    public final LinkedList<o> b;
    private int d = 0;
    private byte[] e;
    private int f;

    private l(Context context) {
        this.a = context;
        this.b = o.a((String) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.l()));
    }

    public static l a(Context context) {
        if (c == null) {
            synchronized (l.class) {
                if (c == null) {
                    c = new l(context);
                }
            }
        }
        return c;
    }

    private p a(int i) {
        double d;
        long j;
        Bundle bundle;
        double d2;
        String i2 = cn.jiguang.sdk.impl.b.i(this.a);
        long e = cn.jiguang.sdk.impl.b.e(this.a);
        String b = cn.jiguang.ap.i.b(this.a);
        long currentTimeMillis = System.currentTimeMillis();
        Object a = cn.jiguang.ah.d.a(this.a, "get_loc_info", null);
        double d3 = 200.0d;
        if (a instanceof Bundle) {
            try {
                bundle = (Bundle) a;
                d2 = bundle.getDouble("lat");
            } catch (Throwable unused) {
                d = 200.0d;
            }
            try {
                d3 = bundle.getDouble("lot");
                j = bundle.getLong("time");
                d3 = d2;
                d = d3;
            } catch (Throwable unused2) {
                double d4 = d3;
                d3 = d2;
                d = d4;
                j = currentTimeMillis;
                return new p(i, i2, JCoreManager.SDK_VERSION, e, b, d3, d, j);
            }
        } else {
            j = currentTimeMillis;
            d = 200.0d;
        }
        return new p(i, i2, JCoreManager.SDK_VERSION, e, b, d3, d, j);
    }

    private synchronized void a(o oVar) {
        this.b.add(oVar);
        cn.jiguang.ai.a.c("SisConnContext", "addSisReportInfo:" + oVar.a().toString());
        while (this.b.size() > 30) {
            this.b.removeFirst();
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<o> it = this.b.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        cn.jiguang.ae.c.a(this.a, cn.jiguang.ae.b.l().a((cn.jiguang.ae.b<String>) jSONArray.toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a(InetAddress inetAddress, int i, DatagramSocket datagramSocket, byte[] bArr) {
        try {
            byte[] a = c.a(c.a(datagramSocket, new DatagramPacket(bArr, bArr.length, inetAddress, i)));
            if (a == null || a.length == 0) {
                throw new Exception("byte could not be empty");
            }
            return ((short) (a.length == 1 ? a[0] : ((short) (a[1] & UByte.MAX_VALUE)) | ((short) ((a[0] & UByte.MAX_VALUE) << 8)))) == 0;
        } catch (Throwable th) {
            cn.jiguang.ai.a.i("SisConnContext", "report failed : " + th);
            return false;
        }
    }

    public final int a() {
        if (this.d == 0) {
            this.d = cn.jiguang.aj.c.a().a(this.a);
        }
        cn.jiguang.aj.c.a();
        int b = cn.jiguang.aj.c.b(this.d);
        cn.jiguang.ai.a.c("SisConnContext", "ipvsupport=" + this.d + ", prefer=" + b);
        return b;
    }

    public final n a(long j) {
        FutureTask futureTask = new FutureTask(new j(this));
        this.d = 0;
        cn.jiguang.sdk.impl.b.a(futureTask);
        try {
            return (n) futureTask.get(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            return null;
        }
    }

    public final void a(int i, String str, int i2, long j, long j2, int i3) {
        if (h.a(str, i2)) {
            o oVar = new o();
            oVar.a = cn.jiguang.sdk.impl.b.i(this.a);
            oVar.b = i;
            oVar.c = new h(str, i2);
            oVar.e = j;
            oVar.f = j2;
            oVar.k = i3;
            oVar.g = cn.jiguang.ap.i.a(this.a);
            oVar.d = cn.jiguang.sdk.impl.b.e(this.a);
            oVar.h = 200.0d;
            oVar.i = 200.0d;
            oVar.j = System.currentTimeMillis();
            Object a = cn.jiguang.ah.d.a(this.a, "get_loc_info", null);
            if (a instanceof Bundle) {
                try {
                    Bundle bundle = (Bundle) a;
                    oVar.h = bundle.getDouble("lat");
                    oVar.i = bundle.getDouble("lot");
                    oVar.j = bundle.getLong("time");
                } catch (Throwable unused) {
                }
            }
            a(oVar);
        }
    }

    public final void a(h hVar) {
        if (((Boolean) cn.jiguang.ae.c.a(this.a, cn.jiguang.ae.b.r())).booleanValue()) {
            if (cn.jiguang.ap.k.a(((Long) cn.jiguang.ae.c.a(this.a, cn.jiguang.ae.b.t())).longValue(), 3600000L)) {
                cn.jiguang.sdk.impl.b.a(new m(this, hVar));
            } else {
                cn.jiguang.ai.a.c("SisConnContext", "sis report: not yet");
            }
        }
    }

    public final void a(boolean z) {
        this.d = (z ? 1 : 2) | this.d;
        cn.jiguang.aj.c.a().a(this.a, this.d);
    }

    public final byte[] a(Set<String> set) {
        int a = cn.jiguang.ap.i.a(this.a);
        if (this.e == null || a != this.f) {
            this.f = a;
            try {
                this.e = c.a("UG", a(a).a(set).toString());
            } catch (Exception e) {
                throw new cn.jiguang.ah.f(2, "Failed to package data - " + e);
            }
        }
        return this.e;
    }
}
