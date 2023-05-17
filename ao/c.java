package cn.jiguang.ao;

import android.os.HandlerThread;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c extends HandlerThread {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(b bVar, String str) {
        super(str);
        this.a = bVar;
    }

    @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            super.run();
        } catch (RuntimeException e) {
            cn.jiguang.ai.a.i("TaskHandlerManager_xxx", "handler thread run e:" + e + "  t=" + Thread.currentThread().getName() + "_" + Thread.currentThread().getId());
        }
    }
}
