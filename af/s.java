package cn.jiguang.af;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s extends ThreadPoolExecutor {
    final /* synthetic */ r a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(r rVar, int i, int i2, long j, TimeUnit timeUnit, BlockingQueue blockingQueue) {
        super(i, i2, 0L, timeUnit, blockingQueue);
        this.a = rVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (r3.hasMessages(233) == false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0025 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // java.util.concurrent.ThreadPoolExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void afterExecute(java.lang.Runnable r2, java.lang.Throwable r3) {
        /*
            r1 = this;
            r2 = 1
            int r3 = r1.getActiveCount()     // Catch: java.lang.Throwable -> L22
            if (r3 > r2) goto L20
            java.util.concurrent.BlockingQueue r3 = r1.getQueue()     // Catch: java.lang.Throwable -> L22
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L22
            if (r3 == 0) goto L20
            cn.jiguang.af.r r3 = r1.a     // Catch: java.lang.Throwable -> L22
            android.os.Handler r3 = cn.jiguang.af.r.a(r3)     // Catch: java.lang.Throwable -> L22
            r0 = 233(0xe9, float:3.27E-43)
            boolean r3 = r3.hasMessages(r0)     // Catch: java.lang.Throwable -> L22
            if (r3 != 0) goto L20
            goto L22
        L20:
            r3 = 0
            goto L23
        L22:
            r3 = 1
        L23:
            if (r3 == 0) goto L37
            cn.jiguang.af.r r3 = r1.a     // Catch: java.lang.Throwable -> L37
            java.util.concurrent.atomic.AtomicBoolean r3 = cn.jiguang.af.r.b(r3)     // Catch: java.lang.Throwable -> L37
            r3.set(r2)     // Catch: java.lang.Throwable -> L37
            cn.jiguang.af.r r2 = r1.a     // Catch: java.lang.Throwable -> L37
            java.util.concurrent.CountDownLatch r2 = cn.jiguang.af.r.c(r2)     // Catch: java.lang.Throwable -> L37
            r2.countDown()     // Catch: java.lang.Throwable -> L37
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.s.afterExecute(java.lang.Runnable, java.lang.Throwable):void");
    }
}
