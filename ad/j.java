package cn.jiguang.ad;

import android.content.Context;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
final class j implements Runnable {
    private final Context a;

    private j(Context context) {
        this.a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ j(Context context, byte b) {
        this(context);
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            String a = i.a(this.a);
            LinkedHashSet b = i.b(this.a);
            b.addAll(i.a());
            cn.jiguang.ac.d.b("ReportSis", "sis urls=" + b.toString() + " post json=" + a);
            if (!cn.jiguang.ap.a.c(this.a)) {
                cn.jiguang.ac.d.f("ReportSis", "give up sis, because network is not connected");
                return;
            }
            Iterator it = b.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!TextUtils.isEmpty(str) && i.a(this.a, str, a)) {
                    return;
                }
            }
        } catch (Throwable unused) {
        }
    }
}
