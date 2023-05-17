package cn.jiguang.ao;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends Handler {
    final /* synthetic */ b a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(b bVar, Looper looper) {
        super(looper);
        this.a = bVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        SparseArray sparseArray;
        SparseArray sparseArray2;
        try {
            sparseArray = this.a.d;
            a aVar = (a) sparseArray.get(message.what);
            if (aVar == null) {
                cn.jiguang.ai.a.g("TaskHandlerManager_xxx", "miss task:" + message.what);
                return;
            }
            if (aVar.c == 1) {
                sendEmptyMessageDelayed(message.what, aVar.b);
            } else {
                sparseArray2 = this.a.d;
                sparseArray2.remove(message.what);
            }
            aVar.a(message);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("TaskHandlerManager_xxx", "handleMessage,e:" + th);
        }
    }
}
