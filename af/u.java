package cn.jiguang.af;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class u extends Handler {
    private final ExecutorService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(ExecutorService executorService) {
        super(r.c());
        this.a = executorService;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        try {
            if (this.a.isShutdown()) {
                cn.jiguang.ai.a.g("Step", "executor is shutdown");
            } else {
                this.a.submit((Callable) message.obj);
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.i("Step", "handleMessage e:" + th);
        }
    }
}
