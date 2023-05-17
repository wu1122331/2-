package cn.jiguang.af;

import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.net.DatagramSocket;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ l b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(l lVar, h hVar) {
        this.b = lVar;
        this.a = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DatagramSocket datagramSocket;
        Object th;
        StringBuilder sb;
        boolean a;
        try {
            datagramSocket = new DatagramSocket();
        } catch (Throwable th2) {
            datagramSocket = null;
            th = th2;
        }
        try {
            String str = (String) cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.l());
            if (TextUtils.isEmpty(str)) {
                cn.jiguang.ai.a.c("SisConnContext", "reportInfo is Empty, quit report");
                try {
                    datagramSocket.close();
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    sb = new StringBuilder("sisReport ,close udpsocket error:");
                }
            } else {
                byte[] a2 = c.a("DG", str);
                cn.jiguang.ai.a.c("SisConnContext", "sis report data(" + a2.length + ") at " + this.a.c + CertificateUtil.DELIMITER + this.a.b);
                l lVar = this.b;
                a = l.a(this.a.c, this.a.b, datagramSocket, a2);
                if (a) {
                    cn.jiguang.ai.a.c("SisConnContext", "report succeed : " + str);
                    cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.t().a((cn.jiguang.ae.b<Long>) Long.valueOf(SystemClock.elapsedRealtime())));
                    cn.jiguang.ae.c.a(this.b.a, cn.jiguang.ae.b.l().a((cn.jiguang.ae.b<String>) null));
                } else {
                    cn.jiguang.ai.a.g("SisConnContext", "report failed" + str);
                }
                try {
                    datagramSocket.close();
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    sb = new StringBuilder("sisReport ,close udpsocket error:");
                }
            }
        } catch (Throwable th5) {
            th = th5;
            try {
                cn.jiguang.ai.a.g("SisConnContext", "sisReport failed, error:" + th);
                if (datagramSocket == null) {
                    return;
                }
                try {
                    datagramSocket.close();
                    return;
                } catch (Throwable th6) {
                    th = th6;
                    sb = new StringBuilder("sisReport ,close udpsocket error:");
                }
            } catch (Throwable th7) {
                if (datagramSocket != null) {
                    try {
                        datagramSocket.close();
                    } catch (Throwable th8) {
                        cn.jiguang.ai.a.g("SisConnContext", "sisReport ,close udpsocket error:" + th8.getMessage());
                    }
                }
                throw th7;
            }
        }
        cn.jiguang.ai.a.g("SisConnContext", sb.append(th.getMessage()).toString());
    }
}
