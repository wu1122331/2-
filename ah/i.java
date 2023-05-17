package cn.jiguang.ah;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import androidx.core.view.PointerIconCompat;
import com.facebook.GraphResponse;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class i {
    private static volatile i i;
    private static final Object j = new Object();
    private cn.jiguang.af.i a;
    private int b;
    private int c;
    private long f;
    private boolean g;
    private Context k;
    private int d = 0;
    private int e = 0;
    private boolean l = false;
    private final AtomicBoolean m = new AtomicBoolean(false);
    private cn.jiguang.ao.a n = new j(this);
    private boolean h = true;

    private i() {
    }

    public static i a() {
        if (i == null) {
            synchronized (j) {
                if (i == null) {
                    i = new i();
                }
            }
        }
        return i;
    }

    private synchronized void a(Context context) {
        if (this.l) {
            return;
        }
        if (context == null) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "init context is null");
            return;
        }
        cn.jiguang.ai.a.c("JCoreTCPManager", "init tcp manager...");
        this.k = context.getApplicationContext();
        cn.jiguang.sdk.impl.b.a("JCoreTCPManager");
        cn.jiguang.ao.b.a().a(this.k);
        g.a().a(context, true);
        this.l = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(i iVar, Context context) {
        String str;
        cn.jiguang.ai.a.c("JCoreTCPManager", "handleResume...");
        cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.a().a((cn.jiguang.ae.b<Boolean>) false));
        if (iVar.g) {
            str = "[handleResume] is loggedin";
        } else if (iVar.a == null) {
            iVar.h();
            return;
        } else {
            str = "[handleResume] tcp is connecting...";
        }
        cn.jiguang.ai.a.c("JCoreTCPManager", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(i iVar, int i2) {
        iVar.b = i2;
        if (i2 == 1012) {
            cn.jiguang.af.c.a(iVar.k);
        }
        iVar.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(i iVar, Context context) {
        cn.jiguang.ai.a.c("JCoreTCPManager", "handleStop...");
        if (((Boolean) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.a())).booleanValue()) {
            cn.jiguang.ai.a.d("JCoreTCPManager", "tcp already stoped");
            return;
        }
        b.a();
        if (!b.a(0)) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "Action: handleStopPush - can't stop tcp");
            return;
        }
        cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.a().a((cn.jiguang.ae.b<Boolean>) true));
        iVar.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Bundle bundle) {
        if (this.m.get()) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "isBeating, skip this time");
            return;
        }
        if (!(bundle != null ? bundle.getBoolean("force", false) : false)) {
            if (System.currentTimeMillis() - this.f < 18000) {
                cn.jiguang.ai.a.c("JCoreTCPManager", "No need to rtc, Because it have succeed recently");
                return;
            }
        }
        cn.jiguang.ai.a.d("JCoreTCPManager", "Send heart beat");
        cn.jiguang.ao.b.a().b(1005);
        if (!this.g) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "socket is closed or push isn't login");
            return;
        }
        this.m.set(true);
        cn.jiguang.ao.b.a().b(1022);
        Long valueOf = Long.valueOf(cn.jiguang.af.c.b(this.k));
        long e = cn.jiguang.sdk.impl.b.e(this.k);
        cn.jiguang.ai.a.c("JCoreTCPManager", "heartbeat - juid:" + e + ", flag:1");
        long longValue = valueOf.longValue();
        int i2 = cn.jiguang.sdk.impl.a.f;
        cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(20480);
        eVar.b(0);
        eVar.a(4);
        eVar.a(2);
        eVar.b(longValue);
        eVar.a(i2);
        eVar.b(e);
        eVar.a(1);
        eVar.b(eVar.a(), 0);
        byte[] a = cn.jiguang.ak.b.a(this.k, eVar.b());
        if (a != null) {
            this.a.c().a(a);
        } else {
            cn.jiguang.ai.a.h("JCoreTCPManager", "send hb failed:sendData is null");
        }
        cn.jiguang.ao.b.a().b(1022, 10000L, this.n);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void d(i iVar) {
        cn.jiguang.ai.a.d("JCoreTCPManager", "Action - onDisconnected");
        if (iVar.g) {
            iVar.g = false;
            b.a().a(iVar.k, -1, -1, "push connect break");
        }
        if (iVar.a == null && ((Boolean) cn.jiguang.ae.c.a(iVar.k, cn.jiguang.ae.b.a())).booleanValue()) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "push already stopped!!!");
            return;
        }
        iVar.e = 0;
        iVar.f();
        iVar.g();
        iVar.d++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e(i iVar) {
        cn.jiguang.ai.a.d("JCoreTCPManager", "Action - onLoggedIn");
        if (!iVar.g) {
            iVar.g = true;
            b.a().a(iVar.k, 1, 0, GraphResponse.SUCCESS_KEY);
        }
        iVar.b();
        iVar.d = 0;
        iVar.e = 0;
        Bundle bundle = new Bundle();
        bundle.putBoolean("login", true);
        d.a(iVar.k, "periodtask", bundle);
        if (iVar.e()) {
            cn.jiguang.ao.b.a().b(2000, 2000L, iVar.n);
            cn.jiguang.am.a.a().a(iVar.k);
            o.a().a(iVar.k);
            g.a().a(iVar.k, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        if (cn.jiguang.sdk.impl.b.q(this.k) || q.a().b(this.k)) {
            return true;
        }
        cn.jiguang.ai.a.c("JCoreTCPManager", "not keep tcp");
        this.h = false;
        f();
        return false;
    }

    private void f() {
        cn.jiguang.af.i iVar = this.a;
        if (iVar == null) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "tcp has stopeed");
            return;
        }
        iVar.b();
        this.a = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void f(i iVar) {
        iVar.m.set(false);
        iVar.e++;
        cn.jiguang.ai.a.d("JCoreTCPManager", "Action - onHeartbeatTimeout - timeoutTimes:" + iVar.e);
        cn.jiguang.ai.a.b("JCoreTCPManager", "============================================================");
        if (iVar.a != null && !iVar.g) {
            cn.jiguang.ai.a.d("JCoreTCPManager", "Is connecting now. Give up to retry.");
        } else if (!iVar.g || iVar.e > 1) {
            iVar.f();
            iVar.g();
        } else {
            cn.jiguang.ai.a.d("JCoreTCPManager", "Already logged in. Give up to retry.");
            cn.jiguang.ao.b.a().b(1005, 5000L, iVar.n);
        }
    }

    private void g() {
        cn.jiguang.ai.a.d("JCoreTCPManager", "Action - retryConnect - disconnectedTimes:" + this.d);
        if (!cn.jiguang.ap.a.c(this.k.getApplicationContext())) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "[retryConnect] network is not connect");
        } else if (this.c > 0) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "[retryConnect] registerErrCode >0,registerErrCode:" + this.c);
        } else {
            int b = cn.jiguang.ap.a.b(this.k.getApplicationContext());
            int pow = (int) (Math.pow(2.0d, this.d) * 3.0d * 1000.0d);
            int i2 = cn.jiguang.sdk.impl.a.b;
            int i3 = (i2 * 1000) / 2;
            if (pow > i3) {
                pow = i3;
            }
            cn.jiguang.ai.a.c("JCoreTCPManager", "[retryConnect] mDisconnectedTimes:" + this.d + ",chargedLever:" + b + ",heartbeatInterval:" + i2 + ",delayTime:" + pow);
            if (b != 1 ? this.d >= 5 : this.d >= 30) {
                cn.jiguang.ai.a.c("JCoreTCPManager", "Give up to retry connect.");
            } else if (cn.jiguang.ao.b.a().a(PointerIconCompat.TYPE_COPY)) {
                cn.jiguang.ai.a.c("JCoreTCPManager", "Already has MSG_RESTART_CONN");
            } else {
                cn.jiguang.ao.b.a().b(PointerIconCompat.TYPE_COPY, pow, this.n);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void g(i iVar) {
        cn.jiguang.ai.a.d("JCoreTCPManager", "Action - onHeartbeatSucceed");
        b.a().a(iVar.k, 19, 0, "ack success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void h() {
        cn.jiguang.ai.a.d("JCoreTCPManager", "Action - restartNetworkingClient, pid:" + Process.myPid());
        if (!this.h) {
            cn.jiguang.ai.a.f("JCoreTCPManager", "need not keep tcp,next start app will re login");
        } else if (!cn.jiguang.ap.a.c(this.k.getApplicationContext())) {
            cn.jiguang.ai.a.f("JCoreTCPManager", "No network connection. Give up to start connection thread.");
        } else if (((Boolean) cn.jiguang.ae.c.a(this.k, cn.jiguang.ae.b.a())).booleanValue()) {
            cn.jiguang.ai.a.d("JCoreTCPManager", "[restartNetworkingClient] tcp has close by active");
        } else {
            int i2 = this.c;
            if (i2 != 1005 && i2 != 1006 && i2 != 1008 && i2 != 1009) {
                if (this.b == 102) {
                    cn.jiguang.ai.a.h("JCoreTCPManager", "login failed:102,give up start connection thread.reset from next app start");
                    return;
                } else if (this.a != null) {
                    cn.jiguang.ai.a.d("JCoreTCPManager", "NetworkingClient is running");
                    return;
                } else {
                    cn.jiguang.af.i iVar = new cn.jiguang.af.i(this.k.getApplicationContext());
                    this.a = iVar;
                    iVar.a();
                    return;
                }
            }
            cn.jiguang.ai.a.c("JCoreTCPManager", "[restartNetworkingClient] registerErrCode >0,registerErrCode:" + this.c);
        }
    }

    public final void a(Context context, String str, Bundle bundle) {
        a(context);
        cn.jiguang.sdk.impl.b.a("JCoreTCPManager", new k(this, context, str, bundle));
    }

    public final void a(Bundle bundle) {
        long j2;
        if (((Boolean) cn.jiguang.ae.c.a(this.k, cn.jiguang.ae.b.a())).booleanValue()) {
            cn.jiguang.ai.a.d("JCoreTCPManager", "[rtc] tcp has close by active");
            return;
        }
        boolean z = true;
        if (bundle != null) {
            z = bundle.getBoolean("force", true);
            j2 = bundle.getLong("delay_time", 0L);
        } else {
            j2 = 0;
        }
        if (this.a == null) {
            h();
            return;
        }
        if (j2 <= 0) {
            c(bundle);
        } else {
            int i2 = PointerIconCompat.TYPE_WAIT;
            if (z) {
                cn.jiguang.ao.b.a().b(1005);
                cn.jiguang.ao.b.a().b(PointerIconCompat.TYPE_WAIT);
            }
            cn.jiguang.ao.b a = cn.jiguang.ao.b.a();
            if (!z) {
                i2 = 1005;
            }
            a.b(i2, j2, this.n);
        }
        cn.jiguang.ai.a.e("JCoreTCPManager", "send rtc force=" + z + " delay=" + j2);
    }

    public final void b() {
        cn.jiguang.ao.b.a().b(1022);
        this.f = System.currentTimeMillis();
        this.e = 0;
        this.m.set(false);
        cn.jiguang.ai.a.e("JCoreTCPManager", "update rtc state");
    }

    public final void b(Bundle bundle) {
        if (((Boolean) cn.jiguang.ae.c.a(this.k, cn.jiguang.ae.b.a())).booleanValue()) {
            cn.jiguang.ai.a.g("JCoreTCPManager", "[netWorkChanged] tcp has close by active");
            return;
        }
        cn.jiguang.ao.b.a().b(PointerIconCompat.TYPE_CELL);
        cn.jiguang.ao.b.a().b(PointerIconCompat.TYPE_CROSSHAIR);
        if (!bundle.getBoolean("connected", false)) {
            cn.jiguang.ai.a.c("JCoreTCPManager", "Handle disconnected state.");
            cn.jiguang.ao.b.a().b(PointerIconCompat.TYPE_CROSSHAIR, 3000L, this.n);
            return;
        }
        cn.jiguang.ai.a.c("JCoreTCPManager", "Handle connected state.");
        if (this.a == null) {
            h();
        } else {
            cn.jiguang.ao.b.a().b(PointerIconCompat.TYPE_CELL, 3000L, this.n);
        }
    }

    public final cn.jiguang.af.i c() {
        return this.a;
    }

    public final boolean d() {
        return this.g;
    }
}
