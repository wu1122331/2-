package cn.jiguang.ad;

import android.content.Context;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f implements Runnable {
    final /* synthetic */ Context a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicBoolean atomicBoolean;
        String e;
        try {
            atomicBoolean = d.d;
            atomicBoolean.set(true);
            e = d.e(this.a);
            File[] a = cn.jiguang.as.e.a(e, cn.jiguang.as.f.b);
            if (a != null) {
                for (File file : a) {
                    d.a(this.a, file);
                }
            } else {
                cn.jiguang.ac.d.b("ReportHistory", "no history, no report");
            }
        } finally {
            try {
            } finally {
            }
        }
    }
}
