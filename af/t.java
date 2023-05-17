package cn.jiguang.af;

import android.os.HandlerThread;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class t extends HandlerThread {
    /* JADX INFO: Access modifiers changed from: package-private */
    public t(String str) {
        super(str);
    }

    @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            super.run();
        } catch (Throwable unused) {
        }
    }
}
