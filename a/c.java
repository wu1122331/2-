package cn.jiguang.a;

import android.content.Context;
import android.os.Bundle;
import cn.jiguang.ab.d;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    Context a;
    boolean b;
    String c;
    Bundle d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, boolean z, String str, Bundle bundle) {
        this.a = context;
        this.b = z;
        this.c = str;
        this.d = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.b) {
                if (a.c(this.a)) {
                    d.a().b(this.a, this.c, this.d);
                }
            } else if (a.b(this.a)) {
                d.a();
                d.a(this.a, this.c, this.d);
            }
        } catch (Throwable unused) {
        }
    }
}
