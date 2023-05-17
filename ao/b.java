package cn.jiguang.ao;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseArray;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public final class b {
    private static volatile b a;
    private static final Object b = new Object();
    private boolean c = false;
    private SparseArray<a> d = new SparseArray<>();
    private Handler e;
    private HandlerThread f;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public final void a(int i, long j, a aVar) {
        if (this.e == null) {
            return;
        }
        aVar.b = j;
        aVar.c = 1;
        this.d.put(8000, aVar);
        if (this.e.hasMessages(8000)) {
            cn.jiguang.ai.a.g("TaskHandlerManager_xxx", "registerFixedAction,same action in handler,will replace");
            this.e.removeMessages(8000);
        }
        this.e.sendEmptyMessageDelayed(8000, j);
    }

    public final synchronized void a(Context context) {
        if (this.c) {
            return;
        }
        if (context == null) {
            cn.jiguang.ai.a.c("TaskHandlerManager_xxx", "init context is null");
            return;
        }
        cn.jiguang.ai.a.c("TaskHandlerManager_xxx", "init task manager...");
        try {
            HandlerThread handlerThread = this.f;
            if (handlerThread == null || !handlerThread.isAlive()) {
                c cVar = new c(this, "TaskHandlerManager_xxx");
                this.f = cVar;
                cVar.start();
            }
            this.e = new d(this, this.f.getLooper() == null ? Looper.getMainLooper() : this.f.getLooper());
        } catch (Exception unused) {
            this.e = new d(this, Looper.getMainLooper());
        }
        this.c = true;
    }

    public final boolean a(int i) {
        Handler handler = this.e;
        if (handler == null) {
            return false;
        }
        return handler.hasMessages(PointerIconCompat.TYPE_COPY);
    }

    public final void b(int i) {
        if (this.e == null) {
            return;
        }
        this.d.remove(i);
        this.e.removeMessages(i);
    }

    public final void b(int i, long j, a aVar) {
        if (this.e == null) {
            return;
        }
        aVar.c = 2;
        this.d.put(i, aVar);
        if (this.e.hasMessages(i)) {
            cn.jiguang.ai.a.c("TaskHandlerManager_xxx", "sendMsg,replace:" + i);
            this.e.removeMessages(i);
        } else {
            cn.jiguang.ai.a.c("TaskHandlerManager_xxx", "sendMsg,action=" + i);
        }
        this.e.sendEmptyMessageDelayed(i, j);
    }
}
