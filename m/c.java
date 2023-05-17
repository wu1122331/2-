package cn.jiguang.m;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class c implements Handler.Callback {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar) {
        this.a = bVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Thread thread;
        if (message == null || message.what != 1 || (thread = (Thread) message.obj) == null) {
            return false;
        }
        thread.interrupt();
        return false;
    }
}
