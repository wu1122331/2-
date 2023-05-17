package cn.jiguang.af;

import android.content.Context;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class i implements Runnable {
    public boolean a;
    private Context b;
    private cn.jiguang.an.a c = new cn.jiguang.an.b(8128, 20);
    private ExecutorService d;

    static {
        cn.jiguang.sdk.impl.b.a("NetworkingClient");
    }

    public i(Context context) {
        this.b = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0073 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(int r10) {
        /*
            r9 = this;
        L0:
            boolean r0 = r9.a
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            java.lang.String r0 = "NetworkingClient"
            if (r10 > 0) goto L16
            java.lang.String r10 = "login error,retry login too many times"
            cn.jiguang.ai.a.d(r0, r10)
            r9.e()
            r9.d()
            return r1
        L16:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "loginTimes:"
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            cn.jiguang.ai.a.c(r0, r2)
            android.content.Context r2 = r9.b
            boolean r2 = cn.jiguang.sdk.impl.b.h(r2)
            r3 = 1
            java.lang.String r4 = "resCode"
            r5 = 0
            if (r2 == 0) goto L40
            android.content.Context r2 = r9.b
            java.lang.String r2 = cn.jiguang.sdk.impl.b.f(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L70
        L40:
            android.content.Context r2 = r9.b
            cn.jiguang.an.a r6 = r9.c
            int r2 = cn.jiguang.af.c.b(r2, r6)
            if (r2 == 0) goto L65
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            r6.putInt(r4, r2)
            cn.jiguang.ah.i r2 = cn.jiguang.ah.i.a()
            android.content.Context r7 = r9.b
            java.lang.String r8 = "tcp_a13"
            r2.a(r7, r8, r6)
            r9.e()
            r9.d()
            r2 = 0
            goto L71
        L65:
            cn.jiguang.ah.i r2 = cn.jiguang.ah.i.a()
            android.content.Context r6 = r9.b
            java.lang.String r7 = "tcp_a11"
            r2.a(r6, r7, r5)
        L70:
            r2 = 1
        L71:
            if (r2 != 0) goto L74
            return r1
        L74:
            android.content.Context r2 = r9.b
            cn.jiguang.an.a r6 = r9.c
            int r2 = cn.jiguang.af.c.a(r2, r6)
            if (r2 >= 0) goto L82
            r9.d()
            return r1
        L82:
            if (r2 <= 0) goto Lba
            r9.e()
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L94
            android.content.Context r0 = r9.b
            cn.jiguang.sdk.impl.b.k(r0)
            int r10 = r10 + (-1)
            goto L0
        L94:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r3 = "Action - onLoginFailed - respCode:"
            r10.<init>(r3)
            java.lang.StringBuilder r10 = r10.append(r2)
            java.lang.String r10 = r10.toString()
            cn.jiguang.ai.a.a(r0, r10)
            android.os.Bundle r10 = new android.os.Bundle
            r10.<init>()
            r10.putInt(r4, r2)
            cn.jiguang.ah.i r10 = cn.jiguang.ah.i.a()
            android.content.Context r0 = r9.b
            java.lang.String r2 = "tcp_a12"
            r10.a(r0, r2, r5)
            return r1
        Lba:
            cn.jiguang.ah.i r10 = cn.jiguang.ah.i.a()
            android.content.Context r0 = r9.b
            java.lang.String r1 = "tcp_a10"
            r10.a(r0, r1, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.i.a(int):boolean");
    }

    private boolean a(Context context) {
        String str;
        cn.jiguang.ai.a.c("NetworkingClient", "google:false");
        cn.jiguang.ap.c.a(context);
        try {
            this.c = new k(l.a(context)).a(this);
        } catch (Exception e) {
            d();
            str = "sis and connect failed:" + e;
        }
        if (a(2)) {
            return true;
        }
        str = "login failed";
        cn.jiguang.ai.a.g("NetworkingClient", str);
        return false;
    }

    private void d() {
        cn.jiguang.ai.a.d("NetworkingClient", "Action - closeConnection");
        cn.jiguang.ap.k.a(this.c);
        cn.jiguang.ah.i.a().a(this.b, "tcp_a19", null);
    }

    private void e() {
        cn.jiguang.ae.c.a(this.b, cn.jiguang.ae.b.b(true).a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.b(false).a((cn.jiguang.ae.b<String>) null));
    }

    public final synchronized void a() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.d = newSingleThreadExecutor;
        newSingleThreadExecutor.execute(this);
    }

    public final synchronized void b() {
        cn.jiguang.ai.a.d("NetworkingClient", "Action - stop");
        cn.jiguang.ap.k.a(this.c);
        this.a = true;
        ExecutorService executorService = this.d;
        if (executorService != null) {
            try {
                executorService.shutdown();
                if (!executorService.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
                        cn.jiguang.ai.a.a("NetworkingClient", "executor did not terminate");
                    }
                }
            } catch (InterruptedException unused) {
                executorService.shutdownNow();
                cn.jiguang.ai.a.a("NetworkingClient", "current thread is interrupted by self");
                Thread.currentThread().interrupt();
            }
        }
    }

    public final cn.jiguang.an.a c() {
        return this.c;
    }

    @Override // java.lang.Runnable
    public final void run() {
        cn.jiguang.ai.a.f("NetworkingClient", "Begin to run in ConnectingThread - id:" + Thread.currentThread().getId());
        try {
        } catch (Throwable th) {
            cn.jiguang.ai.a.d("NetworkingClient", "run exception", th);
        }
        if (!a(this.b)) {
            cn.jiguang.ai.a.d("NetworkingClient", "prepare Push Channel failed , returned");
            return;
        }
        while (!this.a) {
            cn.jiguang.ai.a.d("NetworkingClient", "Network listening...");
            try {
                ByteBuffer a = this.c.a(0);
                cn.jiguang.sdk.impl.b.a("NetworkingClient", new v(this.b, a.array()));
                cn.jiguang.ai.a.d("NetworkingClient", "Received bytes - len:" + a.array().length + ", pkg:" + cn.jiguang.ap.a.a(this.b));
            } catch (cn.jiguang.ah.f e) {
                cn.jiguang.ai.a.g("NetworkingClient", " recv failed with error:" + e + " ,No Break!!");
            }
        }
        if (this.a) {
            cn.jiguang.ai.a.d("NetworkingClient", "Break receiving by wantStop");
        }
        d();
    }
}
