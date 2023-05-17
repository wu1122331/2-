package cn.jiguang.ah;

import android.content.Context;
import android.os.Message;
import cn.jiguang.api.JCoreManager;

/* loaded from: classes.dex */
public final class m extends cn.jiguang.ao.a implements Runnable {
    private Context a;
    private int d;
    private final Object e;
    private int f;

    public m(Context context, int i, Object obj) {
        this.a = context;
        this.d = i;
        this.e = obj;
    }

    public m(Context context, Object obj) {
        this.a = context;
        this.e = obj;
    }

    public static /* synthetic */ int a(m mVar, int i) {
        mVar.f = i;
        return i;
    }

    @Override // cn.jiguang.ao.a
    public final void a(Message message) {
        if (message.what >= 60000) {
            if (this.e instanceof cn.jiguang.af.h) {
                cn.jiguang.ai.a.c("TcpReporter", "time to idle=" + this.e);
                l.a().a(this.a, (cn.jiguang.af.h) this.e, 0);
            }
        } else if (message.what >= 50000) {
            Object obj = this.e;
            if (obj instanceof n) {
                n nVar = (n) obj;
                cn.jiguang.ai.a.c("TcpReporter", "onTimeout=" + nVar);
                l.a().a(nVar.g, nVar.f);
                l.a().a(this.a, nVar);
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i = this.d;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        Object obj = this.e;
                        if (obj instanceof n) {
                            n nVar = (n) obj;
                            cn.jiguang.ai.a.c("TcpReporter", "onResult, data=" + nVar + " code=" + this.f);
                            JCoreManager.onEvent(this.a, cn.jiguang.sdk.impl.a.d, 61, "", null, Integer.valueOf(this.f), nVar.b, nVar.c, Integer.valueOf(nVar.a), nVar.d, nVar.e, nVar.j);
                        }
                    }
                } else if (this.e instanceof cn.jiguang.af.h) {
                    l.b(l.a(), this.a, (cn.jiguang.af.h) this.e);
                }
            } else if (this.e instanceof cn.jiguang.af.h) {
                l.a(l.a(), this.a, (cn.jiguang.af.h) this.e);
            }
        } catch (Throwable unused) {
        }
    }
}
