package cn.jiguang.af;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class r {
    private final ThreadPoolExecutor b;
    private final Handler c;
    private Object g;
    private final AtomicBoolean d = new AtomicBoolean(true);
    private volatile long f = 0;
    private CountDownLatch h = new CountDownLatch(1);
    private final int a = 2000;
    private final a<Object> e = null;

    public r(int i, int i2, a<Object> aVar) {
        s sVar = new s(this, 5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.b = sVar;
        this.c = new u(sVar);
    }

    private synchronized long b(long j) {
        long j2 = this.f + j;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (j2 < uptimeMillis) {
            this.f = uptimeMillis;
            return -1L;
        }
        this.f = j2;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Looper c() {
        t tVar = new t("Step");
        tVar.start();
        Looper looper = tVar.getLooper();
        return looper == null ? Looper.getMainLooper() : looper;
    }

    public final synchronized Object a(long j) {
        if (a()) {
            return this.g;
        } else if (this.d.get()) {
            return null;
        } else {
            try {
                if (j != -1) {
                    this.h.await(j, TimeUnit.MILLISECONDS);
                } else {
                    this.h.await();
                }
            } catch (InterruptedException unused) {
            }
            if (this.h.getCount() == 0) {
                this.h = new CountDownLatch(1);
            }
            return this.g;
        }
    }

    public final void a(Object obj) {
        if (a() || obj == null) {
            return;
        }
        this.g = obj;
        this.h.countDown();
        this.c.removeMessages(233);
        this.b.shutdownNow();
    }

    public final void a(Callable<?> callable) {
        try {
            long b = b(this.a);
            if (b < 0) {
                this.b.submit(callable);
            } else {
                Message obtain = Message.obtain();
                obtain.what = 233;
                obtain.obj = callable;
                this.c.sendMessageAtTime(obtain, b);
            }
            this.d.set(false);
        } catch (Throwable unused) {
        }
    }

    public final boolean a() {
        return this.g != null;
    }

    public final void b() {
        try {
            ThreadPoolExecutor threadPoolExecutor = this.b;
            if (threadPoolExecutor != null) {
                threadPoolExecutor.getQueue().clear();
                this.c.removeMessages(233);
            }
        } catch (Throwable th) {
            Log.w("Step", "clean executor e:" + th);
        }
    }
}
